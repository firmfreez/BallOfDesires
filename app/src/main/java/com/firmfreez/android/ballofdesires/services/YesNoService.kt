package com.firmfreez.android.ballofdesires.services

import com.firmfreez.android.ballofdesires.models.YesNoModel
import com.firmfreez.android.ballofdesires.network.Api
import io.reactivex.Observable
import javax.inject.Inject

class YesNoService @Inject constructor() {
    @Inject
    lateinit var api: Api

    fun getAnswer(): Observable<YesNoModel> {
        return api.getAnswer()
    }
}