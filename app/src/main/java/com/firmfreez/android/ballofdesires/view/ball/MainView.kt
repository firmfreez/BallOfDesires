package com.firmfreez.android.ballofdesires.view.ball

import com.firmfreez.android.ballofdesires.models.YesNoModel
import com.firmfreez.android.ballofdesires.view.baseMVP.BaseView

interface MainView: BaseView<MainPresenter> {
    fun setBallAnswer(data: YesNoModel)
    fun showLoader(boolean: Boolean)
}