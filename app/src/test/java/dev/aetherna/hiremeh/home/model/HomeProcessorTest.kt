package dev.aetherna.hiremeh.home.model

import com.nhaarman.mockitokotlin2.mock
import dev.aetherna.hiremeh.common.domain.Post
import dev.aetherna.hiremeh.common.repository.Repository
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import org.junit.Test
import org.mockito.Mockito.`when`
import java.lang.IllegalStateException

class HomeProcessorTest {

    private val testPosts = listOf(Post.EMPTY.copy(id = "AwesumId"))
    private val postsSource = Observable.just(testPosts)
    private val postRepository: Repository = mock {
        on { getAllPosts() }.thenReturn(postsSource)
    }
    private val testScheduler = Schedulers.trampoline()
    private val processor = HomeProcessor(postRepository, testScheduler, testScheduler)

    @Test
    fun `When action load post and repository returns data successfully then the results are loading and then result with data`() {

        Observable.just(HomeAction.LoadData)
            .compose(processor.process())
            .test()
            .assertValueCount(2)
            .assertValueAt(0) { it is HomeResult.DataLoading }
            .assertValueAt(1) { it is HomeResult.DataLoaded && it.posts == testPosts }
    }

    @Test
    fun `When action load post and repository returns an error THEN result is data loading error`() {

        `when`(postRepository.getAllPosts()).thenReturn(Observable.error(IllegalStateException("Api ded")))

        Observable.just(HomeAction.LoadData)
            .compose(processor.process())
            .test()
            .assertNoErrors()
            .assertValueAt(0) { it is HomeResult.DataLoading }
            .assertValueAt(1) { it is HomeResult.DataLoadingError && it.errorMessage == "Api ded" }
    }
}