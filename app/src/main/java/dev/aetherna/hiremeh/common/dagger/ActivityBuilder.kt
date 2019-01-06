package dev.aetherna.hiremeh.common.dagger


import dagger.Module
import dagger.android.ContributesAndroidInjector
import dev.aetherna.hiremeh.common.dagger.screens.HomeActivityModule
import dev.aetherna.hiremeh.home.HomeActivity

@Module(
    includes = [
        HomeActivityModule::class
    ]
)
interface ActivityBuilder {

    @ContributesAndroidInjector(modules = [HomeActivityModule::class])
    fun bindHomeActivity(): HomeActivity

}