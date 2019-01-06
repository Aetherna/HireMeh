package dev.aetherna.hiremeh.home.view

import dev.aetherna.hiremeh.common.mvi.MviIntent

sealed class HomeIntent : MviIntent

object Initialize : HomeIntent()
object DoNothing : HomeIntent()