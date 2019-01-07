package dev.aetherna.hiremeh.common.api.retrofit.parser

import dev.aetherna.hiremeh.common.api.domain.PostResponse
import dev.aetherna.hiremeh.common.domain.Post

class PostParser : ResponseParser<PostResponse, Post> {
    override fun parse(response: PostResponse) = Post(
        id = response.id,
        title = response.title.orEmpty(),
        body = response.body.orEmpty()
    )
}