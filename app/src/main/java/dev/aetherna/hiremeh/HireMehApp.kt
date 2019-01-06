package dev.aetherna.hiremeh

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dev.aetherna.hiremeh.common.dagger.DaggerAppComponent

class HireMehApp : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {

        return DaggerAppComponent.builder()
            .application(this)
            .build()
            .also {
                it.inject(this)
            }
    }

}