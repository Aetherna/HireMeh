package dev.aetherna.hiremeh.common.repository

import dev.aetherna.hiremeh.common.domain.Post
import io.reactivex.Observable

interface DataSource {
    fun getAllPosts(): Observable<List<Post>>
}