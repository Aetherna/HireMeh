package dev.aetherna.hiremeh.common.repository

import dev.aetherna.hiremeh.common.domain.Post
import dev.aetherna.hiremeh.home.view.PostDetails
import io.reactivex.Observable
import io.reactivex.Single

class PostRepository(
    private val remotePostsSource: DataSource
) : Repository {

    override fun getAllPosts(): Observable<List<Post>> {
        return remotePostsSource.getAllPosts()
    }

    override fun getPostDetails(postId: String): Observable<PostDetails> {
        return remotePostsSource.getPostDetail(postId)
    }
}