package dev.aetherna.hiremeh.common.api.retrofit.parser

import dev.aetherna.hiremeh.common.api.domain.UserResponse
import dev.aetherna.hiremeh.common.domain.User

class UserParser : ResponseParser<UserResponse, User> {
    override fun parse(response: UserResponse) = User(
        id = response.id,
        userName = response.username.orEmpty()
    )
}