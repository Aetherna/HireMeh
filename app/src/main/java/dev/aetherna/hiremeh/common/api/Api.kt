package dev.aetherna.hiremeh.common.api

import dev.aetherna.hiremeh.common.domain.Post
import io.reactivex.Observable

interface Api {
    fun getPosts(): Observable<List<Post>>
}