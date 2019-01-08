package dev.aetherna.hiremeh.common.repository

import dev.aetherna.hiremeh.common.domain.Post
import dev.aetherna.hiremeh.home.view.PostDetails
import io.reactivex.Observable
import io.reactivex.Single

interface DataSource {
    fun getAllPosts(): Observable<List<Post>>

    fun getPostDetail(postId: String): Observable<PostDetails>
}