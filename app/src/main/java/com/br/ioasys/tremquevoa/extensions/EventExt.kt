package com.br.ioasys.tremquevoa.extensions

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import com.br.ioasys.tremquevoa.R
import com.br.ioasys.tremquevoa.domain.model.Event

@SuppressLint("UseCompatLoadingForDrawables")
fun Event.interestImageDrawable(context: Context): Drawable? {
    return activity?.let { activity ->
        if (activity.title.contains("Volei", true)) {
            R.drawable.image_volley
        } else if (activity.title.contains("Futebol", true)) {
            R.drawable.image_soccer
        } else if (activity.title.contains("Meditação", true)) {
            R.drawable.image_meditation
        } else if (activity.title.contains("Estudos", true)) {
            R.drawable.image_study
        } else if (activity.title.contains("Tecnologia", true)) {
            R.drawable.image_tech
        } else if (activity.title.contains("Yoga", true)) {
            R.drawable.image_yoga
        } else if (activity.title.contains("Cinema", true)) {
            R.drawable.image_cinema
        } else if (activity.title.contains("Jogos", true)) {
            R.drawable.image_chess
        } else if (activity.title.contains("Academia", true)) {
            R.drawable.image_gym
        } else if (activity.title.contains("Arte", true)) {
            R.drawable.image_arts
        } else if (activity.title.contains("Musica", true)) {
            R.drawable.image_music
        } else if (activity.title.contains("Corrida", true)) {
            R.drawable.image_runner
        } else {
            R.drawable.rounded_bg_event
        }
    }?.let {
        context.getDrawable(
            it
        )
    }
}