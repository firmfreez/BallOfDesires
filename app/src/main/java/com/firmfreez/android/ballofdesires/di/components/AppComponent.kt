package com.firmfreez.android.ballofdesires.di.components

import com.firmfreez.android.ballofdesires.di.modules.AndroidModule
import com.firmfreez.android.ballofdesires.di.modules.NavigationModule
import com.firmfreez.android.ballofdesires.di.modules.NetworkModule
import com.firmfreez.android.ballofdesires.view.baseUI.BaseActivity
import com.firmfreez.android.ballofdesires.view.dialogs.TextDialogFragment
import com.firmfreez.android.ballofdesires.view.main.MainActivity
import com.firmfreez.android.ballofdesires.view.main.MainPresenter
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AndroidModule::class, NavigationModule::class, NetworkModule::class])
@Singleton
interface AppComponent {
    //Activities
    fun inject(baseActivity: BaseActivity)
    fun inject(mainActivity: MainActivity)

    //Presenters
    fun inject(mainPresenter: MainPresenter)

    //Fragments
    fun inject(textDialogFragment: TextDialogFragment)
}