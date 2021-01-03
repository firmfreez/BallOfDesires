package com.firmfreez.android.ballofdesires.navigation

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.github.terrakok.cicerone.androidx.TransactionInfo

/**
 * Навигатор, позволяющий вызывать DialogFragment-ы как обычные экраны.
 */
class DialogNavigator(activity: FragmentActivity,
                      containerId: Int,
                      private val onExit: (() -> Unit)? = null): AppNavigator(activity, containerId) {

    override fun commitNewFragmentScreen(screen: FragmentScreen, type: TransactionInfo.Type, addToBackStack: Boolean) {
        val fragment = screen.createFragment(fragmentFactory)
        if(fragment is DialogFragment) {
            fragment.show(activity.supportFragmentManager, "dialog1")
        } else super.commitNewFragmentScreen(screen, type, addToBackStack)
    }

    override fun activityBack() {
        onExit?.invoke()
        super.activityBack()
    }

}