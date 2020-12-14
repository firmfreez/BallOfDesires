package com.firmfreez.android.ballofdesires.di.modules

import android.app.Application
import com.firmfreez.android.ballofdesires.view.main.ShakeListener
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AndroidModule(val application: Application) {

    @Singleton
    @Provides
    fun provideApplication(): Application = application

    @Singleton
    @Provides
    fun provideShakeListener(application: Application): ShakeListener = ShakeListener(application)
}