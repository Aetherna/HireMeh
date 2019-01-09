package dev.aetherna.hiremeh.home.model

import dev.aetherna.hiremeh.common.domain.Post
import dev.aetherna.hiremeh.home.view.HomeViewState
import io.reactivex.Observable
import org.junit.Test

class HomeReducerTest {

    private val homeReducer = HomeReducer()
    private val testPosts = listOf(Post.EMPTY.copy(id = "AwesumId"))

    @Test
    fun `When result is success Then state is not loading and contains posts data`() {

        val previousState = HomeViewState.loading
        val successResult = HomeResult.DataLoaded(testPosts)

        //overcomplicated.. yep that why u should write tests first :P
        Observable.just(previousState)
            .zipWith(Observable.just(successResult), homeReducer.reduce())
            .test()
            .assertValue(HomeViewState(isLoading = false, posts = testPosts, error = null))
    }

    @Test
    fun `When result is error and previous state contained loaded posts Then return error state and keep old posts data`() {
        val previousState = HomeViewState(posts = testPosts)
        val errorResult = HomeResult.DataLoadingError("Refresh failed!")

        Observable.just(previousState)
            .zipWith(Observable.just(errorResult), homeReducer.reduce())
            .test()
            .assertValue(HomeViewState(isLoading = false, posts = testPosts, error = "Refresh failed!"))
    }

    @Test
    fun `When result is error and previous state had no posts Then return error state`() {
        val previousState = HomeViewState.loading
        val errorResult = HomeResult.DataLoadingError("Refresh failed!")

        Observable.just(previousState)
            .zipWith(Observable.just(errorResult), homeReducer.reduce())
            .test()
            .assertValue(HomeViewState(isLoading = false, posts = emptyList(), error = "Refresh failed!"))
    }
}