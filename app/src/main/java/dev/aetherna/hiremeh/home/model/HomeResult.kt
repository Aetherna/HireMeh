package dev.aetherna.hiremeh.home.model

import dev.aetherna.hiremeh.common.domain.Post
import dev.aetherna.hiremeh.common.mvi.MviResult

sealed class HomeResult : MviResult {
    class DataLoaded(val posts: List<Post>) : HomeResult()
    class DataLoadingError(val errorMessage: String) : HomeResult()
    object DataLoading : HomeResult()
}

