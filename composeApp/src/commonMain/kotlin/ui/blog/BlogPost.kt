package blog

import jasontoms.composeapp.generated.resources.Res
import kotlinx.datetime.LocalDate

/**
 * A post's markdown lives in `composeResources/files/blog/<date>.md`. The list of posts is
 * generated from those files at build time, so adding a post only needs the markdown file.
 */
data class BlogPost(val date: LocalDate, val title: String) {
    /** The post's identifier in URLs, e.g. `2026-09-21`. */
    val id: String get() = date.toString()

    suspend fun loadMarkdown(): String =
        Res.readBytes("files/blog/$id.md").decodeToString().replaceFirst(frontMatter, "")
}

object BlogPosts {
    val all: List<BlogPost> = generatedBlogPosts

    val latest: BlogPost? get() = all.firstOrNull()

    fun find(date: LocalDate?): BlogPost? = all.firstOrNull { it.date == date }

    /** The post with the given [id], falling back to the newest post if there isn't one. */
    fun resolve(id: String?): BlogPost? = all.firstOrNull { it.id == id } ?: latest
}

/** The `---` block at the top of a post that holds its title; it's read at build time, not rendered. */
private val frontMatter = Regex("""^---\r?\n[\s\S]*?\r?\n---[ \t]*(\r?\n|$)""")
