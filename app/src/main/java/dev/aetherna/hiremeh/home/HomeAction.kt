package dev.aetherna.hiremeh.home

import dev.aetherna.hiremeh.common.mvi.MviAction

sealed class HomeAction : MviAction

object LoadHomeData : HomeAction()