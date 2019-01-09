package dev.aetherna.hiremeh.common.repository.remote

import android.util.Log
import dev.aetherna.hiremeh.common.api.Api
import dev.aetherna.hiremeh.common.domain.Comment
import dev.aetherna.hiremeh.common.domain.Post
import dev.aetherna.hiremeh.common.domain.User
import dev.aetherna.hiremeh.common.repository.DataSource
import dev.aetherna.hiremeh.home.view.PostDetails
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.functions.BiFunction

class RemotePostSource(
    api: Api
) : DataSource {

    private val allPostRequest = api.getPosts().cache()
    private val allUsersRequest = api.getUsers().cache()
    private val allCommentsRequest = api.getComments().cache()

    override fun getAllPosts(): Observable<List<Post>> {
        return allPostRequest
    }

    override fun getPostDetail(postId: String): Observable<PostDetails> {
        return getPostById(postId)
            .flatMap { getDetailsFor(it) }
    }

    private fun getDetailsFor(post: Post): Observable<PostDetails> {
        return Observable.zip(
            getUserForPost(post.userId),
            getCommentsForPost(post.id),
            BiFunction<User, List<Comment>, PostDetails> { user: User, comments: List<Comment> ->
                PostDetails(
                    post.id,
                    user.userName,
                    post.title,
                    post.body,
                    comments.size
                )
            }
        )
    }

    private fun getPostById(postId: String): Observable<Post> = allPostRequest.map { it.find { it.id == postId } }

    private fun getUserForPost(userId: String): Observable<User> = allUsersRequest.map { it.find { it.id == userId } }

    private fun getCommentsForPost(postId: String): Observable<List<Comment>> =
        allCommentsRequest.map { it.filter { it.postId == postId } }
}