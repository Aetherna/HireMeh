package dev.aetherna.hiremeh.details

import android.arch.lifecycle.ViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import dev.aetherna.hiremeh.common.dagger.AppModule
import dev.aetherna.hiremeh.common.dagger.ViewModelKey
import dev.aetherna.hiremeh.common.mvi.MviProcessor
import dev.aetherna.hiremeh.common.mvi.MviReducer
import dev.aetherna.hiremeh.details.model.*
import dev.aetherna.hiremeh.details.view.DetailsViewState
import dev.aetherna.hiremeh.test.TestPostRepository
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton

@Module(
    includes = [
        DetailsTestModule.ProvideViewModel::class,
        AppModule::class
    ]
)
class DetailsTestModule {

    @Provides
    @Singleton
    fun provideDetailsReducer(): MviReducer<DetailsViewState, DetailsResult> {
        return DetailsReducer()
    }

    @Provides
    @Singleton
    fun provideDetailsProcessor(): MviProcessor<DetailsAction, DetailsResult> {
        return DetailsProcessor(TestPostRepository.Instance, Schedulers.trampoline(), Schedulers.trampoline())
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