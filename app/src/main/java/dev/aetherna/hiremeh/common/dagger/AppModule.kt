package dev.aetherna.hiremeh.common.dagger

import dagger.Module
import dagger.Provides
import dev.aetherna.hiremeh.common.api.Api
import dev.aetherna.hiremeh.common.api.ApiModule
import dev.aetherna.hiremeh.common.repository.DataSource
import dev.aetherna.hiremeh.common.repository.PostRepository
import dev.aetherna.hiremeh.common.repository.Repository
import dev.aetherna.hiremeh.common.repository.remote.RemotePostSource
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Named
import javax.inject.Singleton

@Module(
    includes = [
        ApiModule::class
    ]
)
class AppModule {

    @Singleton
    @Provides
    @Named("bgScheduler")
    fun provideBgScheduler(): Scheduler {
        return Schedulers.computation()
    }

    @Singleton
    @Provides
    @Named("uiScheduler")
    fun provideUiScheduler(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

    @Singleton
    @Provides
    @Named("remoteDataSource")
    fun providesRemoteDataSource(
        api: Api
    ): DataSource {
        return RemotePostSource(api)
    }

    @Singleton
    @Provides
    fun provideRepository(
        @Named("remoteDataSource") remoteDataSource: DataSource
    ): Repository {
        return PostRepository(remoteDataSource)
    }
}