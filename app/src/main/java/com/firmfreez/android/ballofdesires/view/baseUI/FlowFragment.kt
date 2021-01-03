package com.firmfreez.android.ballofdesires.view.baseUI

import com.firmfreez.android.ballofdesires.R
import com.firmfreez.android.ballofdesires.navigation.DialogNavigator
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.NavigatorHolder
import javax.inject.Inject

abstract class FlowFragment: BaseFragment() {
    override val layoutRes: Int = R.layout.layout_container

    private val currentFragment
        get() = childFragmentManager.findFragmentById(R.id.container) as? BaseFragment

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    protected val navigator: Navigator by lazy { DialogNavigator(
            activity = requireActivity(),
            containerId = R.id.container,
            onExit = { onExit() }
        )
    }

    override fun onBackPressed() {
        currentFragment?.onBackPressed() ?: super.onBackPressed()
    }

    open fun onExit() {}

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

}