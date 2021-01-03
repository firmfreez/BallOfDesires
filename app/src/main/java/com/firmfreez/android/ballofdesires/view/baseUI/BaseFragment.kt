package com.firmfreez.android.ballofdesires.view.baseUI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * Базовый класс фрагмента
 *
 * Можно добавлять лоэдеры, диалоги и тд...
 */
abstract class BaseFragment: Fragment() {
    abstract val layoutRes: Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(layoutRes, container, false)

    open fun onBackPressed() {}

}
