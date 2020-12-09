package com.firmfreez.android.ballofdesires.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.firmfreez.android.ballofdesires.R
import com.firmfreez.android.ballofdesires.databinding.ActivityMainBinding
import com.firmfreez.android.ballofdesires.models.YesNoModel
import com.firmfreez.android.ballofdesires.view.baseMVP.MVPLifecycleObserver
import com.firmfreez.android.ballofdesires.view.baseMVP.RetainedState

class MainActivity : AppCompatActivity(), MainView {
    private val lazyPresenter by lazy { MainPresenter() }
    private val retainedState: RetainedState by viewModels()
    private lateinit var mLifecycleObserver: MVPLifecycleObserver<MainView, MainPresenter>

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onViewCreated()
    }

    private fun onViewCreated() {
        mLifecycleObserver = MVPLifecycleObserver(this, getPresenter(), retainedState)
        lifecycle.addObserver(mLifecycleObserver)

        binding.startBtn.setOnClickListener {
            getPresenter().onStartBtnClicked()
        }
    }


    override fun setBallAnswer(data: YesNoModel) {
        Glide.with(this)
                .load(data.imageUrl)
                .into(binding.image)

        when (data.answer) {
            YES -> binding.resultText.text = getString(R.string.yes)
            NO  -> binding.resultText.text = getString(R.string.no)
            else  -> binding.resultText.text = getString(R.string.not_defined)
        }
    }

    override fun showLoader(boolean: Boolean) = if(boolean) {
        binding.loader.isVisible = true
        binding.startBtn.isVisible = false
    } else {
        binding.loader.isVisible = false
        binding.startBtn.isVisible = true
    }

    override fun getPresenter(): MainPresenter = lazyPresenter

    private companion object {
        const val YES = "yes"
        const val NO = "no"
        const val TAG = "mainActivity"
    }
}