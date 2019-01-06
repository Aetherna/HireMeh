package dev.aetherna.hiremeh.home

import dev.aetherna.hiremeh.common.mvi.MviResult

sealed class HomeResult : MviResult {
    object NotImplemented : HomeResult()
}

