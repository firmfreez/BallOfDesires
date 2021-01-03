package com.firmfreez.android.ballofdesires.view.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.firmfreez.android.ballofdesires.R
import com.firmfreez.android.ballofdesires.di.App
import com.firmfreez.android.ballofdesires.di.components.DaggerModuleComponent
import com.firmfreez.android.ballofdesires.view.baseMVP.MVPLifecycleObserver
import com.firmfreez.android.ballofdesires.view.baseMVP.RetainedState
import com.firmfreez.android.ballofdesires.view.baseUI.FlowFragment

class MainFlowFragment: FlowFragment(), MainView {
    private val lazyPresenter: MainPresenter by lazy { MainPresenter() }
    private val retainedState: RetainedState by viewModels()
    private lateinit var mLifecycleObserver: MVPLifecycleObserver<MainFlowFragment, MainPresenter>

    override val layoutRes: Int
        get() = R.layout.fragment_main

    init {
        App.instance.component?.let {
            DaggerModuleComponent.factory().create(it).inject(this)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mLifecycleObserver = MVPLifecycleObserver(this, getPresenter(), retainedState)
        lifecycle.addObserver(mLifecycleObserver)


    }

    override fun getPresenter(): MainPresenter = lazyPresenter

    companion object {
        fun newInstance(): MainFlowFragment {
            val args = Bundle()

            val fragment = MainFlowFragment()
            fragment.arguments = args
            return fragment
        }
    }
}