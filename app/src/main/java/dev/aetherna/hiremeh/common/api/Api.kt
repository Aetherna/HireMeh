package dev.aetherna.hiremeh.common.api

import dev.aetherna.hiremeh.common.domain.Comment
import dev.aetherna.hiremeh.common.domain.Post
import dev.aetherna.hiremeh.common.domain.User
import io.reactivex.Observable

interface Api {
    fun getPosts(): Observable<List<Post>>

    fun getUsers(): Observable<List<User>>

    fun getComments(): Observable<List<Comment>>
}