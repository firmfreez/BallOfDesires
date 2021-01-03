package com.firmfreez.android.ballofdesires.di.components

import com.firmfreez.android.ballofdesires.di.modules.ModuleNavigationModule
import com.firmfreez.android.ballofdesires.di.scopes.ModuleScope
import com.firmfreez.android.ballofdesires.view.main.MainFlowFragment
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import dagger.Component

@Component(dependencies = [AppComponent::class],
           modules = [ModuleNavigationModule::class])
@ModuleScope
interface ModuleComponent {
    fun provideCicerone(): Cicerone<Router>

    fun inject(mainFlowFragment: MainFlowFragment)

    @Component.Factory
    interface Factory {
        fun create(appComponent: AppComponent): ModuleComponent
    }
}