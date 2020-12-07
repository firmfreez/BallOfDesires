package com.firmfreez.android.ballofdesires.view.baseMVP

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

/**
 * Наблюдатель за жизненным циклом MVP
 *
 * Соединяет все интерфейсы в одно целое
 *
 * Облегчает прикрепление и открепление презентеров ко вью
 *
 * статья, по которой делал: https://medium.com/swlh/2020-guide-to-implementing-model-view-presenter-architecture-in-android-2ded006e7a8d
 */
class MVPLifecycleObserver<VIEW: BaseView<PRESENTER>, PRESENTER: BasePresenter<VIEW>>(
    private val view: VIEW,
    private val presenter: PRESENTER,
    private val retainedState: RetainedState
) : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        presenter.attach(view, retainedState)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        presenter.detach()
    }
}