package com.firmfreez.android.ballofdesires.view.baseMVP

/**
 * Контракт Presenter-а, определяющий основные методы, доступные ему
 */
interface PresenterContract<VIEW> {
    fun attach(view: VIEW, retainedState: RetainedState)
    fun detach()
    fun onViewAttached()
    fun onViewDetached()
}