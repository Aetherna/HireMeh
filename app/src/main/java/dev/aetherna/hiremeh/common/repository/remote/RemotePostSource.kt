package dev.aetherna.hiremeh.common.repository.remote

import dev.aetherna.hiremeh.common.api.Api
import dev.aetherna.hiremeh.common.domain.Comment
import dev.aetherna.hiremeh.common.domain.Post
import dev.aetherna.hiremeh.common.domain.User
import dev.aetherna.hiremeh.common.repository.DataSource
import io.reactivex.Observable

class RemotePostSource(
    api: Api
) : DataSource {

    private val allPostRequest = api.getPosts().cache()
    private val allUsersRequest = api.getUsers().cache()
    private val allCommentsRequest = api.getComments().cache()

    override fun getAllPosts(): Observable<List<Post>> {
        return allPostRequest
    }

    override fun getAllUsers(): Observable<List<User>> {
        return allUsersRequest
    }

    override fun getAllComments(): Observable<List<Comment>> {
        return allCommentsRequest
    }
}