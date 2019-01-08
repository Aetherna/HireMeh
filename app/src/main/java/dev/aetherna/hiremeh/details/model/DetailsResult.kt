package dev.aetherna.hiremeh.details.model

import dev.aetherna.hiremeh.common.mvi.MviResult
import dev.aetherna.hiremeh.home.view.PostDetails

sealed class DetailsResult : MviResult {
    class Error(val message: String) : DetailsResult()
    class Success(val postDetails: PostDetails) : DetailsResult()
}