package dev.aetherna.hiremeh.home

import android.support.test.filters.MediumTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.view.View
import com.agoda.kakao.*
import dev.aetherna.hiremeh.R
import dev.aetherna.hiremeh.common.domain.Post
import dev.aetherna.hiremeh.test.TestPostRepository
import org.hamcrest.Matcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@Suppress("TestFunctionName")
@MediumTest
@RunWith(AndroidJUnit4::class)
class HomeActivityTest {

    @Rule
    @JvmField
    var activityActivityTestRule = ActivityTestRule(HomeActivity::class.java)

    val postRepository = TestPostRepository.Instance
    val screen = HomeScreen()

    @Test
    fun HAVING_3_posts_loaded_successfully_THEN_home_screen_displays_them_in_order() {

        postRepository.allPostsSubject.onNext(
            listOf(
                emptyPost.copy(title = "title1"),
                emptyPost.copy(title = "title2"),
                emptyPost.copy(title = "title3")
            )
        )

        screen {

            swipeToRefresh { isNotRefreshing() }
            posts {
                childAt<HomeScreen.PostItem>(0) {
                    isVisible()
                    title { hasText("title1") }
                }
                childAt<HomeScreen.PostItem>(1) {
                    isVisible()
                    title { hasText("title2") }
                }
                childAt<HomeScreen.PostItem>(2) {
                    isVisible()
                    title { hasText("title3") }
                }
            }
        }

    }

    @Test
    fun WHEN_error_occurs_when_loading_posts_THEN_displays_a_TOAST_with_error() {

        postRepository.allPostsSubject.onError(IllegalStateException("Oh My!"))

        screen {
            KView {//toast
                withText("Oh My!")
            } perform {
                inRoot { isPlatformPopup() }
            }
        }
    }

    private val emptyPost = Post("", "", "", "")

}

open class HomeScreen : Screen<HomeScreen>() {

    val swipeToRefresh = KSwipeRefreshLayout { withId(R.id.home_refresh) }
    val posts: KRecyclerView = KRecyclerView({
        withId(R.id.home_posts)
    }, itemTypeBuilder = {
        itemType(::PostItem)
    })

    class PostItem(parent: Matcher<View>) : KRecyclerItem<PostItem>(parent) {
        val title: KTextView = KTextView(parent) { withId(R.id.item_post_title) }
        val body: KTextView = KTextView(parent) { withId(R.id.item_post_body) }
    }

}