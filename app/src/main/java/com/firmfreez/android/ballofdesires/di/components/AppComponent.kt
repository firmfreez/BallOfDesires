package com.firmfreez.android.ballofdesires.di.components

import android.content.Context
import com.firmfreez.android.ballofdesires.di.modules.AppModule
import com.firmfreez.android.ballofdesires.di.modules.AppNavigationModule
import com.firmfreez.android.ballofdesires.di.modules.NetworkModule
import com.firmfreez.android.ballofdesires.view.app.AppActivity
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class, AppNavigationModule::class, NetworkModule::class])
@Singleton
interface AppComponent {
    fun provideContext(): Context
    fun provideCicerone(): Cicerone<Router>

    //Activities
    fun inject(appActivity: AppActivity)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }
}