package dev.aetherna.hiremeh.details

import android.support.test.filters.MediumTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.view.View
import com.agoda.kakao.*
import dev.aetherna.hiremeh.R
import dev.aetherna.hiremeh.common.domain.Post
import dev.aetherna.hiremeh.home.view.PostDetails
import dev.aetherna.hiremeh.test.TestPostRepository
import org.hamcrest.Matcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@Suppress("TestFunctionName")
@MediumTest
@RunWith(AndroidJUnit4::class)
class DetailsActivityTest {

    @Rule
    @JvmField
    var activityActivityTestRule = ActivityTestRule(DetailsActivity::class.java)

    val postRepository = TestPostRepository.Instance
    val screen = DetailsScreen()

    @Test
    fun HAVING_post_loaded_successfully_THEN_displays_all_post_details_correctly() {

        postRepository.postDetails.onNext(
            PostDetails("id", "Aetherna", "Sum title", "Lorem Ipsum?", 12)
        )

        screen {

            userName {
                hasText("Aetherna")
            }
            title {
                hasText("Sum title")
            }
            body {
                hasText("Lorem Ipsum?")
            }
            commentsNo {
                hasText("Comments: 12")
            }
        }
    }
}

open class DetailsScreen : Screen<DetailsScreen>() {

    val userName = KTextView { withId(R.id.details_userName) }
    val title = KTextView { withId(R.id.details_title) }
    val body = KTextView { withId(R.id.details_body) }
    val commentsNo = KTextView { withId(R.id.details_comments) }
}