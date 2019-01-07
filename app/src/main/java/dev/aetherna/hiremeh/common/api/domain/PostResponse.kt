package dev.aetherna.hiremeh.common.api.domain

data class PostResponse(
    val userId: String,
    val id: String,
    val title: String?,
    val body: String?
)