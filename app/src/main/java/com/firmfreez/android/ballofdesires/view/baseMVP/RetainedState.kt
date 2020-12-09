package com.firmfreez.android.ballofdesires.view.baseMVP

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

/**
 * Класс необходимый для сохранения состояния Presenter-а
 * SavedStateHandle - способен сохранять информацию в Bundle размером до 1М
 * если будет больше - поймаем TransactionTooLargeException
 */
class RetainedState(private val state: SavedStateHandle): ViewModel() {
    fun <T> get(key: String): T? = state.get(key)

    fun <T> remove(key: String): T? = state.remove(key)

    fun <T> set(key: String, value: T){
        state.set(key, value)
    }
}