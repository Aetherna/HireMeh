package dev.aetherna.hiremeh.home

import android.arch.lifecycle.ViewModel
import dev.aetherna.hiremeh.common.mvi.MviProcessor
import dev.aetherna.hiremeh.common.mvi.MviReducer
import dev.aetherna.hiremeh.common.mvi.MviViewModel
import dev.aetherna.hiremeh.common.util.notOfType
import dev.aetherna.hiremeh.home.view.DoNothing
import dev.aetherna.hiremeh.home.view.HomeIntent
import dev.aetherna.hiremeh.home.view.HomeViewState
import dev.aetherna.hiremeh.home.view.Initialize
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.subjects.PublishSubject

class HomeViewModel(
    private val reducer: MviReducer<HomeViewState, HomeResult>,
    private val processor: MviProcessor<HomeAction, HomeResult>
) : ViewModel(), MviViewModel<HomeIntent, HomeViewState> {

    private val intentsSubject = PublishSubject.create<HomeIntent>()
    private val viewStates = compose()

    private val initializeFilter: ObservableTransformer<HomeIntent, HomeIntent>
        get() = ObservableTransformer { intents ->
            intents.publish { shared ->
                Observable.merge(
                    shared.ofType(Initialize::class.java).take(1),
                    shared.notOfType(DoNothing::class.java)
                )
            }
        }

    override fun processIntents(intents: Observable<HomeIntent>) {
        intents.subscribe(intentsSubject)
    }

    override fun states(): Observable<HomeViewState> = viewStates

    private fun compose(): Observable<HomeViewState> {
        return intentsSubject
            .compose(initializeFilter)
            .map { mapToAction(it) }
            .compose(processor.process())
            .scan(HomeViewState.idle, reducer.reduce())
            .distinctUntilChanged()
            .replay(1)
            .autoConnect(0)
    }

    private fun mapToAction(intent: HomeIntent): HomeAction {
        return when (intent) {
            is Initialize -> LoadHomeData
            is DoNothing -> LoadHomeData
        }
    }
}