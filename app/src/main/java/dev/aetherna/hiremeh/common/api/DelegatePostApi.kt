package dev.aetherna.hiremeh.common.api

import dev.aetherna.hiremeh.common.api.domain.PostResponse
import dev.aetherna.hiremeh.common.api.retrofit.parser.error.ErrorParser
import dev.aetherna.hiremeh.common.api.retrofit.PostApi
import dev.aetherna.hiremeh.common.api.retrofit.parser.ResponseParser
import dev.aetherna.hiremeh.common.domain.Post
import io.reactivex.Observable

class DelegatePostApi(
    private val api: PostApi,
    private val errorParser: ErrorParser,
    private val postParser: ResponseParser<PostResponse, Post>
) : Api {

    override fun getPosts(): Observable<List<Post>> {
        return api.getPosts()
            .flatMap { errorParser.parse(it) }
            .map { it.body?.map { postParser.parse(it) } }
    }

}