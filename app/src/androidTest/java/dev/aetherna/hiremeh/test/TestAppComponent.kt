package dev.aetherna.hiremeh.test

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.ContributesAndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import dev.aetherna.hiremeh.common.api.ApiModule
import dev.aetherna.hiremeh.common.dagger.AppModule
import dev.aetherna.hiremeh.common.dagger.ViewModelModule
import dev.aetherna.hiremeh.details.DetailsActivity
import dev.aetherna.hiremeh.details.DetailsTestModule
import dev.aetherna.hiremeh.home.HomeActivity
import dev.aetherna.hiremeh.home.HomeTestModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ApiModule::class,
        ViewModelModule::class,
        TestActivityModule::class
    ]
)
interface TestAppComponent : AndroidInjector<DaggerApplication> {

    override fun inject(application: DaggerApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: Application): Builder

        fun build(): TestAppComponent
    }
}

@Module(
    includes = [
        HomeTestModule::class,
        DetailsTestModule::class
    ]
)
interface TestActivityModule {

    @ContributesAndroidInjector(modules = [HomeTestModule::class])
    fun bindHomeActivity(): HomeActivity

    @ContributesAndroidInjector(modules = [DetailsTestModule::class])
    fun bindDetailsActivity(): DetailsActivity
}