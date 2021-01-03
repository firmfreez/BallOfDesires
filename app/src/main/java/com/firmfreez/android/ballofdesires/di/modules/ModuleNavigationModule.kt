package com.firmfreez.android.ballofdesires.di.modules

import com.firmfreez.android.ballofdesires.di.scopes.ModuleScope
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides

@Module
class ModuleNavigationModule {
    @Provides
    @ModuleScope
    fun provideRouter(cicerone: Cicerone<Router>): Router = cicerone.router

    @Provides
    @ModuleScope
    fun provideNavigatorHolder(cicerone: Cicerone<Router>): NavigatorHolder = cicerone.getNavigatorHolder()
}