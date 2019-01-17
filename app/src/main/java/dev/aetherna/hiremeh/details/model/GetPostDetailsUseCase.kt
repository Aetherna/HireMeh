package dev.aetherna.hiremeh.details.model

import dev.aetherna.hiremeh.common.domain.Comment
import dev.aetherna.hiremeh.common.domain.Post
import dev.aetherna.hiremeh.common.domain.User
import dev.aetherna.hiremeh.common.repository.Repository
import dev.aetherna.hiremeh.home.view.PostDetails
import io.reactivex.Observable
import io.reactivex.functions.BiFunction

interface PostDetailsUseCase {

    fun getPostDetails(postId: String): Observable<PostDetails>
}

class GetPostDetailsUseCase(
    private val postRepository: Repository
) : PostDetailsUseCase {

    override fun getPostDetails(postId: String): Observable<PostDetails> {
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

    private fun getPostById(postId: String): Observable<Post> =
        postRepository.getAllPosts().map { it.find { it.id == postId } }

    private fun getUserForPost(userId: String): Observable<User> =
        postRepository.getAllUsers().map { it.find { it.id == userId } }

    private fun getCommentsForPost(postId: String): Observable<List<Comment>> =
        postRepository.getAllComments().map { it.filter { it.postId == postId } }
}