package dev.aetherna.hiremeh.home.model

import dev.aetherna.hiremeh.common.mvi.MviAction

sealed class HomeAction : MviAction {
    object LoadData : HomeAction()
}

