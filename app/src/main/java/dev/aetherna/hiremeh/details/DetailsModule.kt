package dev.aetherna.hiremeh.details

import android.arch.lifecycle.ViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import dev.aetherna.hiremeh.common.dagger.AppModule
import dev.aetherna.hiremeh.common.dagger.ViewModelKey
import dev.aetherna.hiremeh.details.model.DetailsViewModel

@Module(
    includes = [
        DetailsModule.ProvideViewModel::class,
        AppModule::class
    ]
)

class DetailsModule {

    @Module
    class ProvideViewModel {

        /* Associate this provider method with FeatureViewModel type in a generated map */
        @Provides
        @IntoMap
        @ViewModelKey(DetailsViewModel::class)
        fun provideHomeViewModel(
//            reducer: MviReducer<HomeViewState, HomeResult>,
//            processor: MviProcessor<HomeAction, HomeResult>
        ): ViewModel = DetailsViewModel()
    }
}