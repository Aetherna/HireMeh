package dev.aetherna.hiremeh.common.repository

import dev.aetherna.hiremeh.common.domain.Comment
import dev.aetherna.hiremeh.common.domain.Post
import dev.aetherna.hiremeh.common.domain.User
import dev.aetherna.hiremeh.home.view.PostDetails
import io.reactivex.Observable
import io.reactivex.Single

interface Repository {
    fun getAllPosts(): Observable<List<Post>>
    fun getAllUsers(): Observable<List<User>>
    fun getAllComments(): Observable<List<Comment>>
}