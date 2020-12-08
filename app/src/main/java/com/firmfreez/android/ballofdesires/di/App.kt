package com.firmfreez.android.ballofdesires.di

import android.app.Application
import com.firmfreez.android.ballofdesires.di.components.AppComponent
import com.firmfreez.android.ballofdesires.di.components.DaggerAppComponent
import com.firmfreez.android.ballofdesires.di.modules.AndroidModule

class App: Application() {
    var component: AppComponent? = null
    get() {
        if(field == null) {
            field = DaggerAppComponent
                .builder()
                .androidModule(AndroidModule(this))
                .build()
        }
        return field
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: App
    }
}