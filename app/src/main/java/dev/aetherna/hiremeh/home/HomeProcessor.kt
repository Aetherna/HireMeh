package dev.aetherna.hiremeh.home

import dev.aetherna.hiremeh.common.mvi.MviProcessor
import io.reactivex.Observable
import io.reactivex.ObservableTransformer

class HomeProcessor : MviProcessor<HomeAction, HomeResult> {

    override fun process() = ObservableTransformer<HomeAction, HomeResult> { action ->
        Observable.just(HomeResult.NotImplemented)
    }
}