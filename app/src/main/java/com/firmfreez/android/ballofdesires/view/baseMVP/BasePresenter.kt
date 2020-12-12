package com.firmfreez.android.ballofdesires.view.baseMVP

import androidx.annotation.CallSuper
import com.firmfreez.android.ballofdesires.di.App
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

/**
 * Базовый презентер, умеет прикрепляться и открепляться от VIEW
 * так же умеет сохранять состояние через RetainedState
 *
 * Необходимо наследоваться для всех Presenter - ов
 */
abstract class BasePresenter<VIEW>: PresenterContract<VIEW> {
    @Inject lateinit var router: Router
    protected var view: VIEW? = null
    protected var retainedState: RetainedState? = null

    override fun attach(view: VIEW, retainedState: RetainedState) {
        this.view = view
        this.retainedState = retainedState
        onViewAttached()
    }

    override fun detach() {
        view = null
        retainedState = null
        onViewDetached()
    }

    /**
     * CallSuper - необходим, для сообщения дочерним классам о необходимости вызова SUPER
     */

    @CallSuper
    override fun onViewAttached() {}

    @CallSuper
    override fun onViewDetached() {}
}