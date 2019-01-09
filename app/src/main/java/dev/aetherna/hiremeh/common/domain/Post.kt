package dev.aetherna.hiremeh.common.domain

data class Post(
    val id: String,
    val userId: String,
    val title: String,
    val body: String
) {
    companion object {
        val EMPTY = Post("", "", "", "")
    }
}