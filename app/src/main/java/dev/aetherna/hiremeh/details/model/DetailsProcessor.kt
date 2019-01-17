package dev.aetherna.hiremeh.details.model

import dev.aetherna.hiremeh.common.mvi.MviProcessor
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.Scheduler

class DetailsProcessor(
    private val postDetailsUseCase: PostDetailsUseCase,
    private val bgScheduler: Scheduler,
    private val uiScheduler: Scheduler
) : MviProcessor<DetailsAction, DetailsResult> {

    override fun process() = ObservableTransformer<DetailsAction, DetailsResult> { action ->
        action.flatMap {
            when (it) {
                is DetailsAction.LoadPost -> loadPost(it.postId)
            }
        }
    }

    private fun loadPost(postId: String): Observable<DetailsResult> {
        return Observable.just(postId)
            .subscribeOn(bgScheduler)
            .flatMap { postDetailsUseCase.getPostDetails(postId) }
            .map { DetailsResult.Success(it) }
            .cast(DetailsResult::class.java)
            .onErrorReturn { DetailsResult.Error(it.message.orEmpty()) }
            .observeOn(uiScheduler)
    }
}