package dev.aetherna.hiremeh.common.repository

import dev.aetherna.hiremeh.common.domain.Post
import io.reactivex.Observable

interface Repository {
    fun getAllPosts(): Observable<List<Post>>
}