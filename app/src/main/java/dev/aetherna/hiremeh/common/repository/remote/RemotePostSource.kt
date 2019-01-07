package dev.aetherna.hiremeh.common.repository.remote

import dev.aetherna.hiremeh.common.api.Api
import dev.aetherna.hiremeh.common.domain.Post
import dev.aetherna.hiremeh.common.repository.DataSource
import io.reactivex.Observable

class RemotePostSource(
    private val api: Api
) : DataSource {

    override fun getAllPosts(): Observable<List<Post>> {
        return api.getPosts()
    }
}