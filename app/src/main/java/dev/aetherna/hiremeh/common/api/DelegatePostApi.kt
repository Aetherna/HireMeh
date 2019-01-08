package dev.aetherna.hiremeh.common.api

import dev.aetherna.hiremeh.common.api.domain.CommentResponse
import dev.aetherna.hiremeh.common.api.domain.PostResponse
import dev.aetherna.hiremeh.common.api.domain.UserResponse
import dev.aetherna.hiremeh.common.api.retrofit.parser.error.ErrorParser
import dev.aetherna.hiremeh.common.api.retrofit.PostApi
import dev.aetherna.hiremeh.common.api.retrofit.parser.ResponseParser
import dev.aetherna.hiremeh.common.domain.Comment
import dev.aetherna.hiremeh.common.domain.Post
import dev.aetherna.hiremeh.common.domain.User
import io.reactivex.Observable

class DelegatePostApi(
    private val api: PostApi,
    private val errorParser: ErrorParser,
    private val postParser: ResponseParser<PostResponse, Post>,
    private val userParser: ResponseParser<UserResponse, User>,
    private val commentParser: ResponseParser<CommentResponse, Comment>
) : Api {

    override fun getPosts(): Observable<List<Post>> {
        return api.getPosts()
            .flatMap { errorParser.parse(it) }
            .map { it.body?.map { postParser.parse(it) } }
    }

    override fun getUsers(): Observable<List<User>> {
        return api.getUsers()
            .flatMap { errorParser.parse(it) }
            .map { it.body?.map { userParser.parse(it) } }
    }

    override fun getComments(): Observable<List<Comment>> {
        return api.getComments()
            .flatMap { errorParser.parse(it) }
            .map { it.body?.map { commentParser.parse(it) } }
    }

}