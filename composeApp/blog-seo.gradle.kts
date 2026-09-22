import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

val blogPostsDir = layout.projectDirectory.dir("src/commonMain/composeResources/files/blog")

val generateBlogIndex = tasks.register<GenerateBlogIndex>("generateBlogIndex") {
    postsDir.set(blogPostsDir)
    outputDir.set(layout.buildDirectory.dir("generated/blogIndex/kotlin"))
}

val generateSeoFiles = tasks.register<GenerateSeoFiles>("generateSeoFiles") {
    postsDir.set(blogPostsDir)
    templateFile.set(layout.projectDirectory.file("src/wasmJsMain/seoTemplate/index.html"))
    postTemplateFile.set(layout.projectDirectory.file("src/wasmJsMain/seoTemplate/post.html"))
    outputDir.set(layout.buildDirectory.dir("generated/seo"))
}

/**
 * A blog post found on disk, parsed from its file name and front matter. Shared by
 * [GenerateBlogIndex] and [GenerateSeoFiles] so both stay in sync with the posts on disk without
 * hand-editing anything when a post is added.
 *
 * Each post is named after its date (`2026-09-21.md`), which is also its URL, and starts with
 * front matter holding its title:
 * ```
 * ---
 * title: My first post
 * ---
 * ```
 */
data class BlogPostFile(val date: LocalDate, val title: String, val body: String) {
    /** The post's identifier in URLs, e.g. `2026-09-21`, matching [BlogPost.id][blog.BlogPost.id]. */
    val id: String get() = date.toString()

    companion object {
        /**
         * The front matter block, capturing its inner lines. Mirrors the regex [BlogPost.loadMarkdown]
         * uses at runtime to strip it, so the [body] read here is exactly what the app renders.
         */
        private val frontMatter = Regex("""^---\r?\n([\s\S]*?)\r?\n---[ \t]*(?:\r?\n|$)""")

        /** Scans [postsDir] for blog posts, since Compose resources can't list a directory at runtime. */
        fun readAll(postsDir: File): List<BlogPostFile> {
            val files = postsDir.listFiles { file -> file.extension == "md" }.orEmpty()
            return files
                .map { file ->
                    val date = runCatching { LocalDate.parse(file.nameWithoutExtension) }.getOrElse {
                        throw GradleException("Blog post ${file.name} must be named after its date, like 2026-09-21.md")
                    }
                    val text = file.readText()
                    val match = frontMatter.find(text)
                        ?: throw GradleException("Blog post ${file.name} must start with front matter (a title between two --- lines)")
                    val title = match.groupValues[1].lineSequence()
                        .map { it.split(":", limit = 2) }
                        .firstOrNull { it.size == 2 && it[0].trim() == "title" }
                        ?.get(1)?.trim()?.removeSurrounding("\"")
                        ?.takeUnless { it.isBlank() }
                        ?: throw GradleException("Blog post ${file.name} is missing a title in its front matter")
                    BlogPostFile(date = date, title = title, body = text.substring(match.range.last + 1))
                }
                .sortedByDescending { it.date }
        }
    }
}

/**
 * A small, purpose-built markdown-to-HTML converter for the static post pages [GenerateSeoFiles]
 * generates. It only needs to cover what the posts in this repo actually use (see the Markdown
 * renderer's own preview content in `BlogScreen.kt` for the same list): headings, bold, italic,
 * inline code, links, blockquotes, fenced code blocks and bullet lists. It is not a full CommonMark
 * implementation — the interactive app still renders the real thing via `markdown-renderer`; this
 * output only has to be honest, readable text for a crawler, not pixel-identical to it.
 */
object SimpleMarkdown {
    private val heading = Regex("""^(#{1,6})\s+(.*)$""")
    private val bullet = Regex("""^\s*[-*]\s+(.*)$""")
    private val blockquote = Regex("""^>\s?(.*)$""")
    private val fence = Regex("""^```.*$""")
    private val link = Regex("""\[([^]]+)]\(([^)\s]+)\)""")
    private val bold = Regex("""\*\*([^*]+)\*\*|__([^_]+)__""")
    private val italic = Regex("""\*([^*]+)\*|_([^_]+)_""")
    private val inlineCode = Regex("""`([^`]+)`""")

    fun toHtml(markdown: String): String {
        val html = StringBuilder()
        var paragraph = mutableListOf<String>()
        var list = mutableListOf<String>()
        var quote = mutableListOf<String>()
        var inFence = false
        val fenceBody = StringBuilder()

        fun flushParagraph() {
            if (paragraph.isNotEmpty()) {
                html.append("<p>").append(inline(paragraph.joinToString(" "))).append("</p>\n")
                paragraph = mutableListOf()
            }
        }

        fun flushList() {
            if (list.isNotEmpty()) {
                html.append("<ul>\n")
                list.forEach { html.append("<li>").append(inline(it)).append("</li>\n") }
                html.append("</ul>\n")
                list = mutableListOf()
            }
        }

        fun flushQuote() {
            if (quote.isNotEmpty()) {
                html.append("<blockquote><p>").append(inline(quote.joinToString(" "))).append("</p></blockquote>\n")
                quote = mutableListOf()
            }
        }

        for (rawLine in markdown.lines()) {
            val line = rawLine.trimEnd('\r')
            if (inFence) {
                if (fence.matches(line)) {
                    html.append("<pre><code>").append(escapeHtml(fenceBody.toString())).append("</code></pre>\n")
                    fenceBody.clear()
                    inFence = false
                } else {
                    fenceBody.append(line).append('\n')
                }
                continue
            }
            when {
                fence.matches(line) -> {
                    flushParagraph(); flushList(); flushQuote(); inFence = true
                }
                line.isBlank() -> {
                    flushParagraph(); flushList(); flushQuote()
                }
                heading.matches(line) -> {
                    flushParagraph(); flushList(); flushQuote()
                    val (hashes, text) = heading.find(line)!!.destructured
                    html.append("<h${hashes.length}>").append(inline(text)).append("</h${hashes.length}>\n")
                }
                blockquote.matches(line) -> {
                    flushParagraph(); flushList()
                    quote.add(blockquote.find(line)!!.groupValues[1].trim())
                }
                bullet.matches(line) -> {
                    flushParagraph(); flushQuote()
                    list.add(bullet.find(line)!!.groupValues[1])
                }
                else -> {
                    flushList(); flushQuote()
                    paragraph.add(line.trim())
                }
            }
        }
        flushParagraph()
        flushList()
        flushQuote()
        return html.toString()
    }

    /** A plain-text excerpt for meta descriptions: the post's first paragraph, stripped of markup. */
    fun excerpt(markdown: String, maxLength: Int): String {
        val firstParagraph = mutableListOf<String>()
        for (rawLine in markdown.lines()) {
            val line = rawLine.trim()
            if (firstParagraph.isEmpty()) {
                if (line.isBlank() || heading.matches(line) || fence.matches(line)) continue
                firstParagraph.add(line)
            } else {
                if (line.isBlank()) break
                firstParagraph.add(line)
            }
        }
        val text = firstParagraph.joinToString(" ")
            .let { link.replace(it) { m -> m.groupValues[1] } }
            .let { bold.replace(it) { m -> m.groupValues[1].ifEmpty { m.groupValues[2] } } }
            .let { italic.replace(it) { m -> m.groupValues[1].ifEmpty { m.groupValues[2] } } }
            .let { inlineCode.replace(it) { m -> m.groupValues[1] } }
        if (text.length <= maxLength) return text
        return text.take(maxLength).substringBeforeLast(' ') + "…"
    }

    private fun inline(text: String): String {
        var result = escapeHtml(text)
        // code spans first, so their contents aren't mistaken for emphasis markers below
        result = inlineCode.replace(result) { "<code>${it.groupValues[1]}</code>" }
        result = link.replace(result) { "<a href=\"${it.groupValues[2]}\">${it.groupValues[1]}</a>" }
        result = bold.replace(result) { "<strong>${it.groupValues[1].ifEmpty { it.groupValues[2] }}</strong>" }
        result = italic.replace(result) { "<em>${it.groupValues[1].ifEmpty { it.groupValues[2] }}</em>" }
        return result
    }

    private fun escapeHtml(text: String) = text.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;")
}

/** Builds the list of blog posts the app displays, from the markdown files in [postsDir]. */
abstract class GenerateBlogIndex : DefaultTask() {
    @get:InputFiles
    @get:PathSensitive(PathSensitivity.RELATIVE)
    abstract val postsDir: DirectoryProperty

    @get:OutputDirectory
    abstract val outputDir: DirectoryProperty

    @TaskAction
    fun generate() {
        val posts = BlogPostFile.readAll(postsDir.get().asFile)

        val entries = posts.joinToString(separator = "") { (date, title) ->
            "    BlogPost(date = LocalDate(${date.year}, ${date.monthValue}, ${date.dayOfMonth}), title = \"${title.escaped()}\"),\n"
        }
        val output = outputDir.get().asFile.resolve("blog/GeneratedBlogPosts.kt")
        output.parentFile.mkdirs()
        output.writeText(
            """
            |// Generated by the generateBlogIndex Gradle task from composeResources/files/blog. Do not edit.
            |package blog
            |
            |import kotlinx.datetime.LocalDate
            |
            |/** Newest first. */
            |internal val generatedBlogPosts: List<BlogPost> = listOf(
            |$entries)
            |""".trimMargin()
        )
    }

    private fun String.escaped() = replace("\\", "\\\\").replace("\"", "\\\"").replace("$", "\\$")
}

/**
 * Generates the static, crawlable files search engines need to find and index the blog: `index.html`
 * (from [templateFile], with the post list filled in and a fresh cache-busting build version stamped
 * onto the composeApp.js reference) plus `sitemap.xml`, built from the same markdown files
 * [generateBlogIndex] reads. This keeps both in sync with the posts on disk without hand-editing
 * either one when a post is added.
 */
abstract class GenerateSeoFiles : DefaultTask() {
    @get:InputFiles
    @get:PathSensitive(PathSensitivity.RELATIVE)
    abstract val postsDir: DirectoryProperty

    @get:InputFile
    @get:PathSensitive(PathSensitivity.NONE)
    abstract val templateFile: RegularFileProperty

    @get:InputFile
    @get:PathSensitive(PathSensitivity.NONE)
    abstract val postTemplateFile: RegularFileProperty

    @get:OutputDirectory
    abstract val outputDir: DirectoryProperty

    @TaskAction
    fun generate() {
        val baseUrl = "https://jasontoms.com"
        val posts = BlogPostFile.readAll(postsDir.get().asFile)
        val out = outputDir.get().asFile
        out.mkdirs()
        val buildVersion = System.currentTimeMillis().toString()

        val postListItems = posts.joinToString(separator = "\n                ") { (date, title) ->
            "<li><a href=\"/blog/${date}\">${title.escapedHtml()}</a> — ${date.format(postDateFormat)}</li>"
        }
        var template = templateFile.get().asFile.readText()
        if (!template.contains(blogPostsMarker)) {
            throw GradleException("${templateFile.get().asFile.name} is missing the $blogPostsMarker marker")
        }
        if (!template.contains(buildVersionMarker)) {
            throw GradleException("${templateFile.get().asFile.name} is missing the $buildVersionMarker marker")
        }
        template = template.replace(blogPostsMarker, postListItems)
        template = template.replace(buildVersionMarker, buildVersion)
        out.resolve("index.html").writeText(template)

        generatePostPages(posts, baseUrl, buildVersion, out)

        val staticPages = listOf("" to "1.0", "portfolio" to "0.8", "blog" to "0.8")
        val urls = staticPages.joinToString(separator = "\n") { (path, priority) ->
            """
            |    <url>
            |        <loc>$baseUrl/$path</loc>
            |        <changefreq>monthly</changefreq>
            |        <priority>$priority</priority>
            |    </url>
            """.trimMargin()
        } + posts.joinToString(separator = "") { (date, _) ->
            """
            |
            |    <url>
            |        <loc>$baseUrl/blog/$date</loc>
            |        <lastmod>$date</lastmod>
            |        <changefreq>monthly</changefreq>
            |        <priority>0.6</priority>
            |    </url>""".trimMargin()
        }
        out.resolve("sitemap.xml").writeText(
            """
            |<?xml version="1.0" encoding="UTF-8"?>
            |<urlset xmlns="http://www.sitemaps.org/schemas/sitemap/0.9">
            |$urls
            |</urlset>
            |""".trimMargin()
        )
    }

    /**
     * Writes one static, crawlable page per post to `blog/<date>.html`. Cloudflare's asset routing
     * (`html_handling: auto-trailing-slash` in wrangler.jsonc) serves this file at the clean URL
     * `/blog/<date>` — the same path the app itself renders that post at, and the one real file
     * Cloudflare finds there before it would otherwise fall back to the SPA shell.
     */
    private fun generatePostPages(posts: List<BlogPostFile>, baseUrl: String, buildVersion: String, out: File) {
        val postTemplate = postTemplateFile.get().asFile
        val rawTemplate = postTemplate.readText()
        listOf(
            postTitleMarker, postTitleJsonMarker, postDescriptionMarker, postUrlMarker,
            postDateIsoMarker, postDateTextMarker, postBodyMarker, buildVersionMarker,
        ).forEach { marker ->
                if (!rawTemplate.contains(marker)) {
                    throw GradleException("${postTemplate.name} is missing the $marker marker")
                }
            }

        val postsDir = out.resolve("blog").apply { mkdirs() }
        posts.forEach { (date, title, body) ->
            val url = "$baseUrl/blog/$date"
            var page = rawTemplate
            page = page.replace(postTitleMarker, title.escapedHtml())
            page = page.replace(postTitleJsonMarker, title.escapedJson())
            page = page.replace(postDescriptionMarker, SimpleMarkdown.excerpt(body, maxLength = 160).escapedHtml())
            page = page.replace(postUrlMarker, url)
            page = page.replace(postDateIsoMarker, date.toString())
            page = page.replace(postDateTextMarker, date.format(postDateFormat))
            page = page.replace(postBodyMarker, SimpleMarkdown.toHtml(body))
            page = page.replace(buildVersionMarker, buildVersion)
            postsDir.resolve("$date.html").writeText(page)
        }
    }

    private fun String.escapedHtml() = replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;")

    private fun String.escapedJson() = replace("\\", "\\\\").replace("\"", "\\\"")

    private companion object {
        const val blogPostsMarker = "<!--BLOG_POSTS-->"
        const val buildVersionMarker = "<!--BUILD_VERSION-->"
        const val postTitleMarker = "<!--POST_TITLE-->"
        const val postTitleJsonMarker = "<!--POST_TITLE_JSON-->"
        const val postDescriptionMarker = "<!--POST_DESCRIPTION-->"
        const val postUrlMarker = "<!--POST_URL-->"
        const val postDateIsoMarker = "<!--POST_DATE_ISO-->"
        const val postDateTextMarker = "<!--POST_DATE_TEXT-->"
        const val postBodyMarker = "<!--POST_BODY-->"
        val postDateFormat: DateTimeFormatter = DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.ENGLISH)
    }
}
