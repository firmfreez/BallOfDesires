package com.firmfreez.android.ballofdesires.di

import android.app.Application
import com.firmfreez.android.ballofdesires.BuildConfig
import com.firmfreez.android.ballofdesires.di.components.AppComponent
import com.firmfreez.android.ballofdesires.di.components.DaggerAppComponent
import com.firmfreez.android.ballofdesires.di.modules.AndroidModule
import timber.log.Timber

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

        //Теперь, если конфигурация DEBUG - будут логи
        //На релизе их не будет
        if(BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    companion object {
        lateinit var instance: App
    }
}