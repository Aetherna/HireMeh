package dev.aetherna.hiremeh.details.model

import android.arch.lifecycle.ViewModel
import dev.aetherna.hiremeh.common.mvi.MviProcessor
import dev.aetherna.hiremeh.common.mvi.MviReducer
import dev.aetherna.hiremeh.common.mvi.MviViewModel
import dev.aetherna.hiremeh.details.view.DetailsIntent
import dev.aetherna.hiremeh.details.view.DetailsViewState
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class DetailsViewModel(
    private val processor: MviProcessor<DetailsAction, DetailsResult>,
    private val reducer: MviReducer<DetailsViewState, DetailsResult>
) : MviViewModel<DetailsIntent, DetailsViewState>, ViewModel() {

    private val intentsSubject = PublishSubject.create<DetailsIntent>()

    override fun processIntents(intents: Observable<DetailsIntent>) {
        intents.subscribe(intentsSubject)
    }

    override fun states(): Observable<DetailsViewState> {
        return intentsSubject
            .map { mapToAction(it) }
            .compose(processor.process())
            .scan(DetailsViewState.loading, reducer.reduce())
            .distinctUntilChanged()
            .replay(1)
            .autoConnect(0)
    }

    private fun mapToAction(intent: DetailsIntent): DetailsAction {
        return when (intent) {
            is DetailsIntent.Initialize -> DetailsAction.LoadPost(intent.postId)
        }
    }

}