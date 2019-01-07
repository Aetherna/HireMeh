package dev.aetherna.hiremeh.common.repository

import dev.aetherna.hiremeh.common.domain.Post
import io.reactivex.Observable

class PostRepository(
    private val remotePostsSource: DataSource
):Repository{

    override fun getAllPosts(): Observable<List<Post>> {
        return remotePostsSource.getAllPosts()
    }

}