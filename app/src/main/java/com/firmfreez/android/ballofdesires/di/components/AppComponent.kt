package com.firmfreez.android.ballofdesires.di.components

import com.firmfreez.android.ballofdesires.di.modules.AndroidModule
import com.firmfreez.android.ballofdesires.view.main.MainPresenter
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AndroidModule::class])
@Singleton
interface AppComponent {
    //Presenters
    fun inject(mainPresenter: MainPresenter)
}