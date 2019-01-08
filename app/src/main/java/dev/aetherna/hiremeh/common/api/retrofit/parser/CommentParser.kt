package dev.aetherna.hiremeh.common.api.retrofit.parser

import dev.aetherna.hiremeh.common.api.domain.CommentResponse
import dev.aetherna.hiremeh.common.domain.Comment

class CommentParser : ResponseParser<CommentResponse, Comment> {
    override fun parse(response: CommentResponse) = Comment(
        id = response.id,
        postId = response.postId
    )
}