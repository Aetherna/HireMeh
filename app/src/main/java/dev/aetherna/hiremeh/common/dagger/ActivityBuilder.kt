package dev.aetherna.hiremeh.common.dagger


import dagger.Module
import dagger.android.ContributesAndroidInjector
import dev.aetherna.hiremeh.details.DetailsActivity
import dev.aetherna.hiremeh.details.DetailsModule
import dev.aetherna.hiremeh.home.HomeModule
import dev.aetherna.hiremeh.home.HomeActivity

@Module(
    includes = [
        HomeModule::class,
        DetailsModule::class
    ]
)
interface ActivityBuilder {

    @ContributesAndroidInjector(modules = [HomeModule::class])
    fun bindHomeActivity(): HomeActivity

    @ContributesAndroidInjector(modules = [DetailsModule::class])
    fun bindDetailsActivity(): DetailsActivity

}