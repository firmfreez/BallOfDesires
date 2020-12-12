package com.firmfreez.android.ballofdesires.view.baseUI

import androidx.appcompat.app.AppCompatActivity
import com.firmfreez.android.ballofdesires.di.App
import com.firmfreez.android.ballofdesires.navigation.DialogNavigator
import com.github.terrakok.cicerone.NavigatorHolder
import javax.inject.Inject

abstract class BaseActivity: AppCompatActivity() {
    private val navigator = DialogNavigator(this, -1)
    @Inject lateinit var navigatorHolder: NavigatorHolder

    init {
        App.instance.component?.inject(this)
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }
}