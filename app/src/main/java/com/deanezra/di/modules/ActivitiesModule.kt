package com.deanezra.di.modules

import com.deanezra.ui.PlantDataActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesModule {
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): PlantDataActivity
}