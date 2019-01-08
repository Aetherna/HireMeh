package dev.aetherna.hiremeh.common.api.retrofit

import dev.aetherna.hiremeh.common.api.domain.CommentResponse
import dev.aetherna.hiremeh.common.api.domain.PostResponse
import dev.aetherna.hiremeh.common.api.domain.UserResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Headers

interface PostApi {

    @GET("posts")
    fun getPosts(): Observable<Response<List<PostResponse>>>

    @GET("users")
    fun getUsers(): Observable<Response<List<UserResponse>>>

    @GET("comments")
    fun getComments(): Observable<Response<List<CommentResponse>>>

}