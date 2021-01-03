package com.firmfreez.android.ballofdesires.di.components

import com.firmfreez.android.ballofdesires.di.modules.FeatureNavigationModule
import com.firmfreez.android.ballofdesires.di.scopes.FeatureScope
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [ModuleComponent::class],
           modules = [FeatureNavigationModule::class])
@FeatureScope
interface FeatureComponent {

    @Component.Factory
    interface Factory {
        fun create(moduleComponent: ModuleComponent): FeatureComponent
    }
}