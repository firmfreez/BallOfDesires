package com.firmfreez.android.ballofdesires.view.main

import com.firmfreez.android.ballofdesires.di.scopes.ModuleScope
import com.firmfreez.android.ballofdesires.view.baseMVP.BasePresenter
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

@ModuleScope
class MainPresenter @Inject constructor(): BasePresenter<MainFlowFragment>() {

    @Inject
    lateinit var router: Router

    fun showYesNoBallFragment() {

    }

    override fun onViewAttached() {
        super.onViewAttached()
    }

    override fun onViewDetached() {
        super.onViewDetached()
    }
}