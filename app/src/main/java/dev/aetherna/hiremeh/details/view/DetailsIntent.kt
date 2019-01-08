package dev.aetherna.hiremeh.details.view

import dev.aetherna.hiremeh.common.mvi.MviIntent

sealed class DetailsIntent : MviIntent {
    class Initialize(val postId: String) : DetailsIntent()
}