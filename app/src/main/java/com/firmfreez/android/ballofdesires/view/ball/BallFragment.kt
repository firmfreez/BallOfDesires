package com.firmfreez.android.ballofdesires.view.ball

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.firmfreez.android.ballofdesires.databinding.FragmentBallBinding
import com.firmfreez.android.ballofdesires.models.YesNoModel
import com.firmfreez.android.ballofdesires.view.baseMVP.MVPLifecycleObserver
import com.firmfreez.android.ballofdesires.view.baseMVP.RetainedState

class BallFragment: Fragment(), BallView {
    private val retainedState: RetainedState by viewModels()
    private val lazyPresenter: BallPresenter by lazy { BallPresenter() }
    private lateinit var binding: FragmentBallBinding
    private lateinit var mLifecycleObserver: MVPLifecycleObserver<BallFragment, BallPresenter>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentBallBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mLifecycleObserver = MVPLifecycleObserver(this, getPresenter(), retainedState)
        lifecycle.addObserver(mLifecycleObserver)
    }


    override fun setBallAnswer(data: YesNoModel) {
        TODO("Not yet implemented")
    }

    override fun showLoader(boolean: Boolean) {
        TODO("Not yet implemented")
    }

    override fun getPresenter(): BallPresenter = lazyPresenter
}