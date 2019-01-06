package dev.aetherna.hiremeh.common.dagger.screens

import android.arch.lifecycle.ViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import dev.aetherna.hiremeh.common.dagger.ViewModelKey
import dev.aetherna.hiremeh.common.mvi.MviProcessor
import dev.aetherna.hiremeh.common.mvi.MviReducer
import dev.aetherna.hiremeh.home.*
import dev.aetherna.hiremeh.home.view.HomeViewState
import javax.inject.Singleton

@Module(
    includes = [
        HomeActivityModule.ProvideViewModel::class
    ]
)
class HomeActivityModule {

    @Provides
    @Singleton
    fun provideHomeReducer(): MviReducer<HomeViewState, HomeResult> {
        return HomeReducer()
    }

    @Provides
    @Singleton
    fun provideHomeProcessor(): MviProcessor<HomeAction, HomeResult> {
        return HomeProcessor()
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