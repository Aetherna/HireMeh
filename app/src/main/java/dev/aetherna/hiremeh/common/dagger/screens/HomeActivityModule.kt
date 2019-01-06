package dev.aetherna.hiremeh.common.dagger.screens

import android.arch.lifecycle.ViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import dev.aetherna.hiremeh.common.dagger.ViewModelKey
import dev.aetherna.hiremeh.home.HomeViewModel

@Module(
    includes = [
        HomeActivityModule.ProvideViewModel::class
    ]
)
class HomeActivityModule {

//    @Provides
//    @Singleton
//    fun provideInteractor(): Interactor {
//        return MainInteractor()
//    }

    @Module
    class ProvideViewModel {

        /* Associate this provider method with FeatureViewModel type in a generated map */
        @Provides
        @IntoMap
        @ViewModelKey(HomeViewModel::class)
//        fun provideMainViewModel(interactor: Interactor): ViewModel = MainViewModel(interactor)
        fun provideHomeViewModel(): ViewModel = HomeViewModel()
    }
}