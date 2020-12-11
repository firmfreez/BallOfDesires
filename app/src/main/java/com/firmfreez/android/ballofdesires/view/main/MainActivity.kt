package com.firmfreez.android.ballofdesires.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.firmfreez.android.ballofdesires.R
import com.firmfreez.android.ballofdesires.databinding.ActivityMainBinding
import com.firmfreez.android.ballofdesires.models.YesNoModel
import com.firmfreez.android.ballofdesires.view.baseMVP.MVPLifecycleObserver
import com.firmfreez.android.ballofdesires.view.baseMVP.RetainedState
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity(), MainView {
    private val lazyPresenter by lazy { MainPresenter() }
    private val retainedState: RetainedState by viewModels()

    private var vibrator: Vibrator? = null
    private var shaker: ShakeListener? = null

    private val MAX_PROGRESS = 100
    private val VIBRATE_DURATION = 1000L
    private val VIBRATE_AMPLITUDE = 0

    private lateinit var mLifecycleObserver: MVPLifecycleObserver<MainView, MainPresenter>
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.progress.max = MAX_PROGRESS

        vibrator = (getSystemService(VIBRATOR_SERVICE) as? Vibrator)
        shaker = ShakeListener(this)
        shaker?.shakeListener = object : ShakeListener.OnShakeListener {
            override fun onShake(progress: Float) {
                binding.progress.progress = (progress * MAX_PROGRESS).roundToInt()
            }

            override fun onComplete() {
                getPresenter().onStartBtnClicked()
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    vibrator?.vibrate(VibrationEffect.createOneShot(VIBRATE_DURATION,VIBRATE_AMPLITUDE))
                } else {
                    vibrator?.vibrate(VIBRATE_DURATION)
                }
            }
        }

        onViewCreated()
    }

    override fun onResume() {
        super.onResume()
        shaker?.resume()
    }

    override fun onPause() {
        super.onPause()
        shaker?.pause()
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
    }
}