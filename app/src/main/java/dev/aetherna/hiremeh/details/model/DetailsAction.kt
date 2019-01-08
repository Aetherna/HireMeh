package dev.aetherna.hiremeh.details.model

import dev.aetherna.hiremeh.common.mvi.MviAction

sealed class DetailsAction : MviAction {
    class LoadPost(val postId: String) : DetailsAction()
}