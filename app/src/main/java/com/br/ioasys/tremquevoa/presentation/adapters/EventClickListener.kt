package com.br.ioasys.tremquevoa.presentation.adapters

import com.br.ioasys.tremquevoa.domain.model.Event

interface EventClickListener {
    fun onEventClickListener(event: Event)
}