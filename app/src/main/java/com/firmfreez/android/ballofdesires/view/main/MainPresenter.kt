package com.firmfreez.android.ballofdesires.view.main

import android.util.Log
import com.firmfreez.android.ballofdesires.di.App
import com.firmfreez.android.ballofdesires.services.YesNoService
import com.firmfreez.android.ballofdesires.view.baseMVP.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.internal.disposables.DisposableContainer
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainPresenter: BasePresenter<MainView>() {
    @Inject lateinit var yesNoService: YesNoService
    private lateinit var compositeDisposable: CompositeDisposable

    init {
        App.instance.component?.inject(this)
    }

    override fun onViewAttached() {
        super.onViewAttached()
        Log.d(TAG, "$TAG attached")
        compositeDisposable = CompositeDisposable()
    }

    override fun onViewDetached() {
        super.onViewDetached()
        Log.d(TAG, "$TAG detached")
        compositeDisposable.clear()
    }

    fun onStartBtnClicked() {
        val disposable = yesNoService.getAnswer()
                .doOnSubscribe {
                    view?.showLoader(true)
                }
                .doFinally {
                    view?.showLoader(false)
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    Log.d(TAG, "Value: ${it.value}; Answer: ${it.answer}; ImageURL: ${it.imageUrl}")
                    view?.setBallAnswer(it)
                }, {
                    Log.e(TAG, it.localizedMessage.orEmpty())
                })
        compositeDisposable.add(disposable)
    }

    private companion object {
        const val TAG = "MainPresenter"
    }
}