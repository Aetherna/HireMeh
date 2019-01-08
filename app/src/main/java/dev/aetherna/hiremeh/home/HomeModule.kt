package dev.aetherna.hiremeh.home

import android.arch.lifecycle.ViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import dev.aetherna.hiremeh.common.dagger.AppModule
import dev.aetherna.hiremeh.common.dagger.ViewModelKey
import dev.aetherna.hiremeh.common.mvi.MviProcessor
import dev.aetherna.hiremeh.common.mvi.MviReducer
import dev.aetherna.hiremeh.common.repository.Repository
import dev.aetherna.hiremeh.home.model.*
import dev.aetherna.hiremeh.home.view.HomeViewState
import io.reactivex.Scheduler
import javax.inject.Named
import javax.inject.Singleton

@Module(
    includes = [
        HomeModule.ProvideViewModel::class,
        AppModule::class
    ]
)
class HomeModule {

    @Provides
    @Singleton
    fun provideHomeReducer(): MviReducer<HomeViewState, HomeResult> {
        return HomeReducer()
    }

    @Provides
    @Singleton
    fun provideHomeProcessor(
        postRepository: Repository,
        @Named("bgScheduler") bgScheduler: Scheduler,
        @Named("uiScheduler") uiScheduler: Scheduler
    ): MviProcessor<HomeAction, HomeResult> {
        return HomeProcessor(postRepository, bgScheduler, uiScheduler)
    }

    @Module
    class ProvideViewModel {

        /* Associate this provider method with FeatureViewModel type in a generated map */
        @Provides
        @IntoMap
        @ViewModelKey(HomeViewModel::class)
        fun provideHomeViewModel(
            reducer: MviReducer<HomeViewState, HomeResult>,
            processor: MviProcessor<HomeAction, HomeResult>
        ): ViewModel = HomeViewModel(reducer, processor)
    }
}