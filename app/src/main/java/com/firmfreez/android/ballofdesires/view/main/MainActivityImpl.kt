package com.firmfreez.android.ballofdesires.view.main

import com.firmfreez.android.ballofdesires.models.YesNoModel
import com.firmfreez.android.ballofdesires.view.baseMVP.BaseView

interface MainActivityImpl: BaseView<MainPresenter> {
    fun setBallAnswer(data: YesNoModel)
    fun showLoader(boolean: Boolean)
}