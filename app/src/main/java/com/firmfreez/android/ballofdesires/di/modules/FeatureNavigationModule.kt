package com.firmfreez.android.ballofdesires.di.modules

import com.firmfreez.android.ballofdesires.di.scopes.FeatureScope
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides

@Module
class FeatureNavigationModule {
    @Provides
    @FeatureScope
    fun provideRouter(cicerone: Cicerone<Router>): Router = cicerone.router

    @Provides
    @FeatureScope
    fun provideNavigatorHolder(cicerone: Cicerone<Router>): NavigatorHolder = cicerone.getNavigatorHolder()
}