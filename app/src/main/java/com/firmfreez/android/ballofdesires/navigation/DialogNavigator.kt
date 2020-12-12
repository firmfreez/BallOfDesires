package com.firmfreez.android.ballofdesires.navigation

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.github.terrakok.cicerone.androidx.TransactionInfo

/**
 * Навигатор, позволяющий вызывать DialogFragment-ы как обычные экраны.
 */
class DialogNavigator(activity: AppCompatActivity, containerId: Int): AppNavigator(activity, containerId) {
    override fun commitNewFragmentScreen(screen: FragmentScreen, type: TransactionInfo.Type, addToBackStack: Boolean) {
        val fragment = screen.createFragment(fragmentFactory)
        if(fragment is DialogFragment) {
            fragment.show(activity.supportFragmentManager, "dialog1")
        } else super.commitNewFragmentScreen(screen, type, addToBackStack)
    }
}