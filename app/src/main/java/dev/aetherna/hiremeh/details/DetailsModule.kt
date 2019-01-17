package dev.aetherna.hiremeh.details

import android.arch.lifecycle.ViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import dev.aetherna.hiremeh.common.dagger.AppModule
import dev.aetherna.hiremeh.common.dagger.ViewModelKey
import dev.aetherna.hiremeh.common.mvi.MviProcessor
import dev.aetherna.hiremeh.common.mvi.MviReducer
import dev.aetherna.hiremeh.common.repository.Repository
import dev.aetherna.hiremeh.details.model.*
import dev.aetherna.hiremeh.details.view.DetailsViewState
import io.reactivex.Scheduler
import javax.inject.Named
import javax.inject.Singleton

@Module(
    includes = [
        DetailsModule.ProvideViewModel::class,
        AppModule::class
    ]
)

class DetailsModule {

    @Provides
    @Singleton
    fun provideDetailsReducer(): MviReducer<DetailsViewState, DetailsResult> {
        return DetailsReducer()
    }

    @Provides
    @Singleton
    fun provideGetDetailsUseCase(postRepository: Repository): PostDetailsUseCase {
        return GetPostDetailsUseCase(postRepository)
    }

    @Provides
    @Singleton
    fun provideDetailsProcessor(
        postDetailsUseCase: PostDetailsUseCase,
        @Named("bgScheduler") bgScheduler: Scheduler,
        @Named("uiScheduler") uiScheduler: Scheduler
    ): MviProcessor<DetailsAction, DetailsResult> {
        return DetailsProcessor(postDetailsUseCase, bgScheduler, uiScheduler)
    }

    @Module
    class ProvideViewModel {

        /* Associate this provider method with FeatureViewModel type in a generated map */
        @Provides
        @IntoMap
        @ViewModelKey(DetailsViewModel::class)
        fun provideDetailsViewModel(
            reducer: MviReducer<DetailsViewState, DetailsResult>,
            processor: MviProcessor<DetailsAction, DetailsResult>
        ): ViewModel = DetailsViewModel(reducer, processor)
    }
}