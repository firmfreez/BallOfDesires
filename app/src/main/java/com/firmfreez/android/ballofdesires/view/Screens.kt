package com.firmfreez.android.ballofdesires.view

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.firmfreez.android.ballofdesires.view.dialogs.TextDialogFragment
import com.firmfreez.android.ballofdesires.view.main.MainFlowFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {

    fun mainFlowFragment() = FragmentScreen { MainFlowFragment.newInstance() }

    fun textDialog(@StringRes title: Int? = null,
                    @StringRes message: Int? = null,
                    @DrawableRes drawableId: Int? = null,
                    applyButtonEnabled: Boolean = true,
                    cancelButtonEnabled: Boolean = true,
                    dismissEnabled: Boolean = true)
            = FragmentScreen { TextDialogFragment.newInstance(
                    title,
                    message,
                    drawableId,
                    applyButtonEnabled,
                    cancelButtonEnabled,
                    dismissEnabled)
            }

}