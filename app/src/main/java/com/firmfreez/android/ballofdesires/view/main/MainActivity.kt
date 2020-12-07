package com.firmfreez.android.ballofdesires.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.firmfreez.android.ballofdesires.R
import com.firmfreez.android.ballofdesires.databinding.ActivityMainBinding
import com.firmfreez.android.ballofdesires.models.YesNoModel
import com.firmfreez.android.ballofdesires.view.baseMVP.MVPLifecycleObserver
import com.firmfreez.android.ballofdesires.view.baseMVP.RetainedState

class MainActivity : AppCompatActivity(), MainActivityImpl {
    private val lazyPresenter by lazy { MainPresenter() }
    private val retainedState: RetainedState by viewModels()
    private lateinit var lifecycleObserver: MVPLifecycleObserver<MainActivityImpl, MainPresenter>

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onViewCreated()
    }

    private fun onViewCreated() {
        lifecycleObserver = MVPLifecycleObserver(this, getPresenter(), retainedState)
        lifecycle.addObserver(lifecycleObserver)

        binding.startBtn.setOnClickListener {
            getPresenter().onStartBtnClicked()
        }
    }


    override fun setBallAnswer(data: YesNoModel) {
        when (data.answer) {
            "yes" -> binding.resultText.text = getString(R.string.yes)
            "no"  -> binding.resultText.text = getString(R.string.no)
            else  -> binding.resultText.text = getString(R.string.not_defined)
        }
    }

    override fun showLoader(boolean: Boolean) = if(boolean) {
        binding.loader.visibility = View.VISIBLE
        binding.startBtn.visibility = View.GONE
    } else {
        binding.loader.visibility = View.GONE
        binding.startBtn.visibility = View.VISIBLE
    }

    override fun getPresenter(): MainPresenter = lazyPresenter
}