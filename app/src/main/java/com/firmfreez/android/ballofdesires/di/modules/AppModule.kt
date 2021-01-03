package com.firmfreez.android.ballofdesires.di.modules

import android.content.Context
import com.firmfreez.android.ballofdesires.view.ball.ShakeListener
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    @Singleton
    @Provides
    fun provideShakeListener(context: Context): ShakeListener = ShakeListener(context)
}