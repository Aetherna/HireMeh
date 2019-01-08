package dev.aetherna.hiremeh.home.model

import dev.aetherna.hiremeh.common.mvi.MviProcessor
import dev.aetherna.hiremeh.common.repository.Repository
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.Scheduler

class HomeProcessor(
    private val postRepository: Repository,
    private val bgScheduler: Scheduler,
    private val uiScheduler: Scheduler
) : MviProcessor<HomeAction, HomeResult> {

    override fun process() = ObservableTransformer<HomeAction, HomeResult> { action ->
        action.flatMap {
            when (it) {
                is HomeAction.LoadData -> loadPosts()
            }
        }
    }

    private fun loadPosts(): Observable<HomeResult> = postRepository.getAllPosts()
        .subscribeOn(bgScheduler)
        .map { it -> HomeResult.DataLoaded(it) }
        .observeOn(uiScheduler)
        .cast(HomeResult::class.java)
        .onErrorReturn { HomeResult.DataLoadingError(it.message.orEmpty()) }
        .startWith(HomeResult.DataLoading)
}