package dev.aetherna.hiremeh.common.dagger

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ApiModule::class,
        ViewModelModule::class,
        ActivityBuilder::class]
)
interface AppComponent : AndroidInjector<DaggerApplication> {

    override fun inject(application: DaggerApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: Application): Builder

//        fun localSourceModule(module: DatabaseModule): Builder

        fun build(): AppComponent
    }

}