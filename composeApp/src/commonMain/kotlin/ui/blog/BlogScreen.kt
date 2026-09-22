package ui.blog

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import blog.BlogPost
import blog.BlogPosts
import com.mikepenz.markdown.coil3.Coil3ImageTransformerImpl
import com.mikepenz.markdown.m3.Markdown
import com.mikepenz.markdown.m3.markdownTypography
import com.mikepenz.markdown.model.markdownPadding
import jasontoms.composeapp.generated.resources.Res
import jasontoms.composeapp.generated.resources.blog_all_posts
import jasontoms.composeapp.generated.resources.blog_empty
import jasontoms.composeapp.generated.resources.blog_load_error
import jasontoms.composeapp.generated.resources.blog_subtitle
import jasontoms.composeapp.generated.resources.blog_title
import kotlinx.coroutines.CancellationException
import kotlinx.datetime.LocalDate
import kotlinx.datetime.format
import kotlinx.datetime.format.MonthNames
import kotlinx.datetime.format.Padding
import kotlinx.datetime.format.char
import navigation.Route
import org.jetbrains.compose.resources.stringResource
import theme.Dimens
import theme.LocalWindowSizeClass
import theme.Previews
import theme.SitePreview
import theme.components.ContentColumn
import theme.components.SelectableText
import theme.sectionSpacing
import ui.footer.Footer

private val sidebarWidth = 280.dp

/**
 * The list of posts sits in a sidebar next to the selected post. On narrow screens there is no
 * room for a sidebar, so the list moves underneath the post instead.
 */
@Composable
fun BlogScreen(
    route: Route.Blog,
    onNavigate: (Route) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(),
) {
    val post = BlogPosts.find(route.date)
    val markdown by produceState<Result<String>?>(initialValue = null, post) {
        value = null
        if (post == null) return@produceState
        value = try {
            Result.success(post.loadMarkdown())
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    BlogContent(
        posts = BlogPosts.all,
        post = post,
        markdown = markdown,
        onNavigate = onNavigate,
        modifier = modifier,
        contentPadding = contentPadding,
    )
}

/**
 * @param posts every post, newest first
 * @param post the post being read, or null when there are no posts
 * @param markdown the post's content, or null while it loads
 */
@Composable
private fun BlogContent(
    posts: List<BlogPost>,
    post: BlogPost?,
    markdown: Result<String>?,
    onNavigate: (Route) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(),
) {
    val compact = LocalWindowSizeClass.current.widthSizeClass == WindowWidthSizeClass.Compact
    val onSelect = { selected: BlogPost -> onNavigate(Route.Blog(selected.date)) }
    val scrollState = rememberScrollState()
    // a newly picked post should be read from its beginning
    LaunchedEffect(post) { scrollState.scrollTo(0) }

    // unlike the other pages, a post can be shorter than the window, so this is a plain scrolling
    // column at least as tall as the window, with a flexible gap that keeps the footer at the bottom
    BoxWithConstraints(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState)
                .heightIn(min = maxHeight),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Column(
                modifier = Modifier.padding(top = contentPadding.calculateTopPadding() + Dimens.medium),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(sectionSpacing()),
            ) {
                ContentColumn {
                    when {
                        post == null -> BlogEmpty()
                        compact -> PostContent(post = post, markdown = markdown)
                        else -> Row(horizontalArrangement = Arrangement.spacedBy(Dimens.extraLarge)) {
                            PostList(
                                modifier = Modifier.width(sidebarWidth),
                                title = stringResource(Res.string.blog_title),
                                subtitle = stringResource(Res.string.blog_subtitle),
                                posts = posts,
                                selected = post,
                                onSelect = onSelect,
                            )
                            PostContent(post = post, markdown = markdown, modifier = Modifier.weight(1f))
                        }
                    }
                }
                if (post != null && compact) {
                    ContentColumn {
                        PostList(
                            title = stringResource(Res.string.blog_all_posts),
                            subtitle = stringResource(Res.string.blog_subtitle),
                            posts = posts,
                            selected = post,
                            onSelect = onSelect,
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(sectionSpacing()))
            Spacer(modifier = Modifier.weight(1f))
            Footer(onNavigate = onNavigate)
        }
    }
}

@Composable
private fun PostList(
    title: String,
    subtitle: String,
    posts: List<BlogPost>,
    selected: BlogPost,
    onSelect: (BlogPost) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(Dimens.xxSmall)) {
        Text(
            modifier = Modifier.padding(start = Dimens.small, bottom = Dimens.xSmall),
            text = title,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground,
        )
        Text(
            modifier = Modifier.padding(start = Dimens.small, bottom = Dimens.xSmall),
            text = subtitle,
            style = MaterialTheme.typography.bodySmall,
            fontStyle = FontStyle.Italic,
            color = MaterialTheme.colorScheme.onBackground,
        )
        posts.forEach { post ->
            PostLink(post = post, selected = post == selected, onClick = { onSelect(post) })
        }
    }
}

@Composable
private fun PostLink(post: BlogPost, selected: Boolean, onClick: () -> Unit) {
    val interactionSource = remember { MutableInteractionSource() }
    val hovered by interactionSource.collectIsHoveredAsState()
    val colors = MaterialTheme.colorScheme
    val background by animateColorAsState(
        when {
            selected -> colors.secondaryContainer
            hovered -> colors.surfaceContainerHighest
            else -> Color.Transparent
        }
    )
    val content by animateColorAsState(if (selected) colors.onSecondaryContainer else colors.onSurface)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.large)
            .background(background)
            .hoverable(interactionSource)
            .pointerHoverIcon(PointerIcon.Hand)
            .selectable(
                selected = selected,
                interactionSource = interactionSource,
                indication = null,
                role = Role.Tab,
                onClick = onClick,
            )
            .padding(horizontal = Dimens.small, vertical = Dimens.xSmall + Dimens.xxSmall),
        verticalArrangement = Arrangement.spacedBy(Dimens.xxSmall),
    ) {
        Text(
            text = post.date.format(shortDate),
            style = MaterialTheme.typography.labelMedium,
            color = content.copy(alpha = 0.75f),
        )
        Text(text = post.title, style = MaterialTheme.typography.titleMedium, color = content)
    }
}

@Composable
private fun PostContent(post: BlogPost, markdown: Result<String>?, modifier: Modifier = Modifier) {
    val compact = LocalWindowSizeClass.current.widthSizeClass == WindowWidthSizeClass.Compact
    val shape = MaterialTheme.shapes.extraLarge

    // long text is hard to read over the dotted background, so the post sits on a card
    Column(
        modifier = modifier
            .widthIn(max = Dimens.maxReadingWidth)
            .background(color = MaterialTheme.colorScheme.surfaceContainerLow.copy(alpha = 0.92f), shape = shape)
            .border(width = 1.dp, color = MaterialTheme.colorScheme.outlineVariant, shape = shape)
            .padding(if (compact) Dimens.medium else Dimens.large),
        verticalArrangement = Arrangement.spacedBy(Dimens.small),
    ) {
        SelectableText(
            text = post.date.format(longDate),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.primary,
        )
        SelectableText(
            text = post.title,
            style = if (compact) MaterialTheme.typography.displaySmall else MaterialTheme.typography.displayMedium,
            color = MaterialTheme.colorScheme.onBackground,
        )
        markdown?.onSuccess { content ->
            SelectionContainer {
                Markdown(
                    modifier = Modifier.fillMaxWidth(),
                    content = content,
                    // post headings sit under the post title, so they start a few sizes smaller
                    // than the renderer's display-sized defaults
                    typography = markdownTypography(
                        h1 = MaterialTheme.typography.headlineLarge,
                        h2 = MaterialTheme.typography.headlineMedium,
                        h3 = MaterialTheme.typography.headlineSmall,
                        h4 = MaterialTheme.typography.titleLarge,
                        h5 = MaterialTheme.typography.titleMedium,
                        h6 = MaterialTheme.typography.titleSmall,
                        quote = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.onSurfaceVariant),
                    ),
                    padding = markdownPadding(block = Dimens.xSmall, codeBlock = PaddingValues(Dimens.small)),
                    imageTransformer = Coil3ImageTransformerImpl,
                )
            }
        }?.onFailure {
            Text(
                text = stringResource(Res.string.blog_load_error),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.error,
            )
        }
    }
}

@Composable
private fun BlogEmpty() {
    Column(verticalArrangement = Arrangement.spacedBy(Dimens.small)) {
        SelectableText(
            text = stringResource(Res.string.blog_title),
            style = MaterialTheme.typography.displayMedium,
            color = MaterialTheme.colorScheme.onBackground,
        )
        SelectableText(
            text = stringResource(Res.string.blog_empty),
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = null),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}

/** e.g. "September 21, 2026" */
private val longDate = LocalDate.Format {
    monthName(MonthNames.ENGLISH_FULL); char(' '); day(padding = Padding.NONE); char(','); char(' '); year()
}

/** e.g. "Sep 21, 2026" */
private val shortDate = LocalDate.Format {
    monthName(MonthNames.ENGLISH_ABBREVIATED); char(' '); day(padding = Padding.NONE); char(','); char(' '); year()
}

private val previewPosts = listOf(
    BlogPost(date = LocalDate(2026, 9, 21), title = "Moving the site to Cloudflare"),
    BlogPost(date = LocalDate(2026, 8, 14), title = "A post with a title long enough to wrap onto a second line"),
    BlogPost(date = LocalDate(2026, 6, 2), title = "Starting a blog"),
)

private val previewMarkdown = """
    This is a test post for previews, with a bit of everything a post might use.

    ## A heading

    Some **bold** text, some *italic* text, `inline code` and [a link](https://jasontoms.com).

    - A bullet
    - Another bullet

    > A quote from someone wise.

    ```kotlin
    fun main() = println("Hello, blog")
    ```
""".trimIndent()

@Previews
@Composable
private fun BlogScreenPreview() {
    SitePreview {
        BlogContent(
            posts = previewPosts,
            post = previewPosts.first(),
            markdown = Result.success(previewMarkdown),
            onNavigate = {},
        )
    }
}
