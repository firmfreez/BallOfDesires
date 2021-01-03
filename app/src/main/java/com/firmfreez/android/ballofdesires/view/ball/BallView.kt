package com.firmfreez.android.ballofdesires.view.ball

import com.firmfreez.android.ballofdesires.models.YesNoModel
import com.firmfreez.android.ballofdesires.view.baseMVP.BaseView

interface BallView: BaseView<BallPresenter> {
    fun setBallAnswer(data: YesNoModel)
    fun showLoader(boolean: Boolean)
}