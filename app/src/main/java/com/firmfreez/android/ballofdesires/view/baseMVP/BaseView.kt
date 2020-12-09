package com.firmfreez.android.ballofdesires.view.baseMVP

/**
 * Базовая VIEW, умеет возвращать Presenter
 * Необходимо наследоваться для всех VIEW у которых есть Presenter
 */
interface BaseView<PRESENTER> {
    fun getPresenter(): PRESENTER
}