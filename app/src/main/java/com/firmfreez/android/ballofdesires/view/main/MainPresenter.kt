package com.firmfreez.android.ballofdesires.view.main

import com.firmfreez.android.ballofdesires.R
import com.firmfreez.android.ballofdesires.di.App
import com.firmfreez.android.ballofdesires.models.YesNoModel
import com.firmfreez.android.ballofdesires.services.YesNoService
import com.firmfreez.android.ballofdesires.view.Screens
import com.firmfreez.android.ballofdesires.view.baseMVP.BasePresenter
import com.firmfreez.android.ballofdesires.view.dialogs.TextDialogFragment
import com.firmfreez.android.ballofdesires.view.dialogs.TextDialogFragment.Companion.TEXT_DIALOG_RESULT
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class MainPresenter: BasePresenter<MainView>() {
    @Inject lateinit var yesNoService: YesNoService
    private lateinit var compositeDisposable: CompositeDisposable

    init {
        App.instance.component?.inject(this)
    }

    override fun onViewAttached() {
        super.onViewAttached()
        Timber.d("attached")
        compositeDisposable = CompositeDisposable()

        //Восстанавливаем данные на форме
        retainedState?.get<YesNoModel>(OLD_DATA)?.let {
            Timber.d(retainedState?.get<YesNoModel>(OLD_DATA).toString())
            view?.setBallAnswer(it)
        }
    }

    override fun onViewDetached() {
        super.onViewDetached()
        Timber.d( "detached")
        compositeDisposable.clear()
    }

    fun onStartBtnClicked() {
        val disposable = yesNoService.getAnswer()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    view?.showLoader(true)
                }
                .doFinally {
                    view?.showLoader(false)
                }
                .subscribe({
                    Timber.d("Value: ${it.value}; Answer: ${it.answer}; ImageURL: ${it.imageUrl}")
                    view?.setBallAnswer(it)
                    retainedState?.set(OLD_DATA, it)
                }, {
                    Timber.e(it.localizedMessage.orEmpty())
                    showErrorDialog()
                })
        compositeDisposable.add(disposable)
    }

    private fun showErrorDialog() {
        router.setResultListener(TEXT_DIALOG_RESULT) {
            when (it as String) {
                TextDialogFragment.APPLY -> {
                    Timber.d("Apply dialog")
                }
                TextDialogFragment.CANCEL -> {
                    Timber.d("Cancel dialog")
                }
                TextDialogFragment.DISMISS -> {
                    Timber.d("Dismiss dialog")
                }
            }
        }

        router.navigateTo(Screens.textDialog(
                drawableId = R.drawable.ic_error_64,
                title = R.string.network_error_title,
                message = R.string.network_error_message,
                applyButtonEnabled = true,
                cancelButtonEnabled = false,
                dismissEnabled = false)
        )
    }

    private companion object {
        const val OLD_DATA = "MainPresenterData"
    }
}