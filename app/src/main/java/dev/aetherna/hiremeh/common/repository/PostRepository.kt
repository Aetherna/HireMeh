package dev.aetherna.hiremeh.common.repository

import dev.aetherna.hiremeh.common.domain.Comment
import dev.aetherna.hiremeh.common.domain.Post
import dev.aetherna.hiremeh.common.domain.User
import io.reactivex.Observable

class PostRepository(
    private val remotePostsSource: DataSource
) : Repository {

    override fun getAllPosts(): Observable<List<Post>> {
        return remotePostsSource.getAllPosts()
    }

    override fun getAllUsers(): Observable<List<User>> {
        return remotePostsSource.getAllUsers()
    }

    override fun getAllComments(): Observable<List<Comment>> {
        return remotePostsSource.getAllComments()
    }
}