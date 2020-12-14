package com.firmfreez.android.ballofdesires.view.dialogs

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import com.firmfreez.android.ballofdesires.databinding.FragmentTextDialogBinding
import com.firmfreez.android.ballofdesires.di.App
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

class TextDialogFragment: DialogFragment() {
    private lateinit var binding: FragmentTextDialogBinding
    private var dismissEnabled = true
    @Inject lateinit var router: Router

    init {
        App.instance.component?.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTextDialogBinding.inflate(inflater, container, false)
        //Скрываем заголовок, чтоб не оставлять белого пространства
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initDissmisMethod()
        initApplyButton()
        initCancelButton()
        initIcon()
        initTitle()
        initMessage()
    }

    private fun initDissmisMethod() {
        dismissEnabled = arguments?.getBoolean(DISMISS_ENABLED) ?: true
    }

    private fun initMessage() {
        arguments?.getInt(MESSAGE_ARG)?.let {
            if (it == 0) {
                binding.message.isVisible = false
            } else {
                binding.message.setText(it)
            }
        }
    }

    private fun initTitle() {
        arguments?.getInt(TITLE_ARG)?.let {
            if (it == 0) {
                binding.title.isVisible = false
            } else {
                binding.title.setText(it)
            }
        }
    }

    private fun initIcon() {
        arguments?.getInt(DRAWABLE_ARG)?.let {
            if (it == 0) {
                binding.icon.isVisible = false
            } else {
                binding.icon.setImageDrawable(ContextCompat.getDrawable(requireContext(), it))
            }
        }
    }

    private fun initCancelButton() {
        if (arguments?.getBoolean(CANCEL_BUTTON_ENABLED, true) == true) {
            binding.cancel.setOnClickListener {
                router.sendResult(TEXT_DIALOG_RESULT, CANCEL)
                dismiss()
            }
        } else {
            binding.cancel.isVisible = false
        }
    }

    private fun initApplyButton() {
        if(arguments?.getBoolean(APPLY_BUTTON_ENABLED, true) == true){
            binding.submit.setOnClickListener {
                router.sendResult(TEXT_DIALOG_RESULT, APPLY)
                dismiss()
            }
        } else {
            binding.submit.isVisible = false
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        if(dismissEnabled) {
            router.sendResult(TEXT_DIALOG_RESULT, DISMISS)
            super.onDismiss(dialog)
        }
    }

    companion object {
        fun newInstance(@StringRes title: Int? = null,
                        @StringRes message: Int? = null,
                        @DrawableRes drawableId: Int? = null,
                        applyButtonEnabled: Boolean = true,
                        cancelButtonEnabled: Boolean = true,
                        dismissEnabled: Boolean = true): TextDialogFragment {
            val args = Bundle()

            title?.let { args.putInt(TITLE_ARG, it) }
            message?.let { args.putInt(MESSAGE_ARG, it) }
            drawableId?.let { args.putInt(DRAWABLE_ARG, it) }
            args.putBoolean(APPLY_BUTTON_ENABLED, applyButtonEnabled)
            args.putBoolean(CANCEL_BUTTON_ENABLED, cancelButtonEnabled)
            args.putBoolean(DISMISS_ENABLED, dismissEnabled)

            val fragment = TextDialogFragment()
            fragment.arguments = args
            return fragment
        }

        private const val TITLE_ARG = "titleDialogTitle"
        private const val MESSAGE_ARG = "messageDialogIcon"
        private const val DRAWABLE_ARG = "drawableDialogIcon"
        private const val APPLY_BUTTON_ENABLED = "apply_button_enabled"
        private const val CANCEL_BUTTON_ENABLED = "cancel_button_enabled"
        private const val DISMISS_ENABLED = "dismiss_enabled"

        const val TEXT_DIALOG_RESULT = "text_dialog_result"

        const val APPLY = "apply"
        const val CANCEL = "cancel"
        const val DISMISS = "dismiss"
    }
}