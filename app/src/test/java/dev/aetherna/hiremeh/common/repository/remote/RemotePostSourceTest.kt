package dev.aetherna.hiremeh.common.repository.remote

import com.nhaarman.mockitokotlin2.mock
import dev.aetherna.hiremeh.common.api.Api
import dev.aetherna.hiremeh.common.domain.Comment
import dev.aetherna.hiremeh.common.domain.Post
import dev.aetherna.hiremeh.common.domain.User
import io.reactivex.Observable
import org.junit.Test

class RemotePostSourceTest {

    private val testPosts = listOf(
        Post.EMPTY.copy(id = "post1", userId = "user1", title = "title1", body = "body1"),
        Post.EMPTY.copy(id = "post2", userId = "user1"),
        Post.EMPTY.copy(id = "post3", userId = "user3")
    )

    private val testUsers = listOf(
        User("user1", "Aetherna"),
        User("user2", "Bob")
    )

    private val testComments = listOf(
        Comment("comment1", "post2"),
        Comment("comment2", "post1"),
        Comment("comment3", "post2"),
        Comment("comment4", "post1")
    )

    private val api: Api = mock {
        on { getPosts() }.thenReturn(Observable.just(testPosts))
        on { getUsers() }.thenReturn(Observable.just(testUsers))
        on { getComments() }.thenReturn(Observable.just(testComments))
    }

    val postSource = RemotePostSource(api)

    @Test
    fun `Having all data present When get post details Then returns data successfully`() {

        postSource.getPostDetail("post1")
            .test()
            .assertNoErrors()
            .assertValue {
                it.id == "post1"
                        && it.userName == "Aetherna"
                it.title == "title1"
                        && it.body == "body1"
                        && it.commentsNo == 2
            }
    }

    @Test
    fun `Having no post data present When get post details Then passes the error further down the stream`() {

        postSource.getPostDetail("nopost")
            .test()
            .assertError { it is NullPointerException }
    }

    @Test
    fun `Having no user for post present When get post details Then passes the error further down the stream`() {

        postSource.getPostDetail("post3")
            .test()
            .assertError { it is NullPointerException }
    }

}