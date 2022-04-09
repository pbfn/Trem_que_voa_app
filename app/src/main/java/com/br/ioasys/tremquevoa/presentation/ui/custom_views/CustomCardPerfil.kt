package com.br.ioasys.tremquevoa.presentation.ui.custom_views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.br.ioasys.tremquevoa.R

class CustomCardPerfil @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val view =
        LayoutInflater.from(context).inflate(R.layout.custom_card_perfil, this, true)

    val title: AppCompatTextView by lazy {
        view.findViewById(R.id.titleCard)
    }

    val icon: AppCompatImageView by lazy {
        view.findViewById(R.id.iconCard)
    }

    val subtitle: AppCompatTextView by lazy {
        view.findViewById(R.id.subTitleCard)
    }

    init {
        setLayout(attrs)
    }

    private fun setLayout(attrs: AttributeSet?) {
        attrs?.let { attributeSet ->
            val attributes =
                context.obtainStyledAttributes(attributeSet, R.styleable.CustomLayoutIoasys)

            val customTitle = attributes.getString(R.styleable.CustomLayoutIoasys_titleCard)
            title.text = customTitle

            val customSubtitle = attributes.getString(R.styleable.CustomLayoutIoasys_subtitleCard)
            subtitle.text = customSubtitle

            val customIcon = attributes.getInt(
                R.styleable.CustomLayoutIoasys_iconCard,
                R.drawable.ic_medal
            )

            icon.background = ContextCompat.getDrawable(context, customIcon)

            attributes.recycle()
        }
    }
}