package com.firmfreez.android.ballofdesires.view.app

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.firmfreez.android.ballofdesires.R
import com.firmfreez.android.ballofdesires.di.App
import com.firmfreez.android.ballofdesires.navigation.DialogNavigator
import com.firmfreez.android.ballofdesires.view.baseMVP.MVPLifecycleObserver
import com.firmfreez.android.ballofdesires.view.baseMVP.RetainedState
import com.firmfreez.android.ballofdesires.view.baseUI.BaseFragment
import com.github.terrakok.cicerone.NavigatorHolder
import javax.inject.Inject

/**
 * Главное активити, рулит FlowFragment-ами внутри себя
 */
class AppActivity: AppCompatActivity(), AppView {
    @Inject lateinit var navigatorHolder: NavigatorHolder
    private lateinit var mLifecycleObserver: MVPLifecycleObserver<AppActivity, AppPresenter>


    private val lazyPresenter by lazy { AppPresenter() }
    private val retainedState: RetainedState by viewModels()
    private val currentFragment: BaseFragment?
        get() = supportFragmentManager.findFragmentById(R.id.container) as? BaseFragment

    init {
        App.instance.component?.inject(this)
    }

    private val lazyNavigator by lazy { DialogNavigator(
            activity = this,
            containerId = R.id.container,
            onExit = { finish() }
    )}

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.layout_container)
        onViewCreated()
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(lazyNavigator)
    }

    private fun onViewCreated() {
        mLifecycleObserver = MVPLifecycleObserver(this, getPresenter(), retainedState)
        lifecycle.addObserver(mLifecycleObserver)
    }

    override fun onBackPressed() {
       currentFragment?.onBackPressed() ?: super.onBackPressed()
    }


    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun getPresenter(): AppPresenter = lazyPresenter
}