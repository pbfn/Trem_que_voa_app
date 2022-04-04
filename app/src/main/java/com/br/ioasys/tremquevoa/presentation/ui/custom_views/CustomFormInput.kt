package com.br.ioasys.tremquevoa.presentation.ui.custom_views

import android.content.Context
import android.text.InputType
import android.transition.TransitionManager
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewTreeObserver
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.br.ioasys.tremquevoa.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class CustomFormInput @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {


    private val view =
        LayoutInflater.from(context).inflate(R.layout.custom_form, this, true)

    val title: AppCompatTextView by lazy {
        view.findViewById(R.id.tvTitle)
    }

    val input: TextInputEditText by lazy {
        view.findViewById(R.id.editText)
    }

    private val inputLayout: TextInputLayout by lazy {
        view.findViewById(R.id.textInputLayout)
    }

    val msgError: AppCompatTextView by lazy {
        view.findViewById(R.id.tvError)
    }

    init {
        setLayout(attrs)
        configure()

    }

    private fun setLayout(attrs: AttributeSet?) {
        attrs?.let { attributeSet ->
            val attributes =
                context.obtainStyledAttributes(attributeSet, R.styleable.CustomFormInput)

            val customTitle = attributes.getString(R.styleable.CustomFormInput_custom_title)
            title.text = customTitle

            val inputType = attributes.getInt(
                R.styleable.CustomFormInput_android_inputType,
                InputType.TYPE_CLASS_TEXT
            )
            input.inputType = inputType

            val messageError =
                attributes.getString(R.styleable.CustomFormInput_msgError)
            msgError.text = messageError

            val background =
                attributes.getInt(
                    R.styleable.CustomFormInput_background_input,
                    R.drawable.input_custom_neutral
                )

            input.background = ContextCompat.getDrawable(context, background)
            attributes.recycle()
        }

    }


    private fun getTextInputLayoutTopSpace(): Int {
        var currentView: View = input
        var space = 0
        do {
            space += currentView.top
            currentView = currentView.parent as View
        } while (currentView.id != inputLayout.id)
        return space
    }

    private fun updateHintPosition(hasFocus: Boolean, hasText: Boolean, animate: Boolean) {
        if (animate) {
            TransitionManager.beginDelayedTransition(inputLayout)
        }
        if (hasFocus || hasText) {
            input.setPadding(50, 0, 0, 0)
        } else {
            input.setPadding(50, 0, 0, getTextInputLayoutTopSpace())
        }
    }

    private fun configure() {
        inputLayout.viewTreeObserver.addOnPreDrawListener(object :
            ViewTreeObserver.OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                // Wait for the first draw to be sure the view is completely measured
                if (inputLayout.height > 0) {
                    inputLayout.viewTreeObserver.removeOnPreDrawListener(this)
                    updateHintPosition(input.hasFocus(), !input.text.isNullOrEmpty(), false)
                    return false
                }
                return true
            }
        })

        input.setOnFocusChangeListener { _, hasFocus ->
            updateHintPosition(hasFocus, !input.text.isNullOrEmpty(), true)
        }
    }


}