package com.firmfreez.android.ballofdesires.network

import com.firmfreez.android.ballofdesires.models.YesNoModel
import io.reactivex.Observable
import retrofit2.http.GET

interface Api {

    @GET("api")
    fun getAnswer(): Observable<YesNoModel>

    companion object {
        const val BACK_URL = "https://yesno.wtf/"
    }
}