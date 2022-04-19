package com.br.ioasys.tremquevoa.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.br.ioasys.tremquevoa.databinding.FragmentEventBinding
import com.br.ioasys.tremquevoa.domain.model.Event
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class EventFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentEventBinding? = null
    private val binding: FragmentEventBinding get() = _binding!!

    private var event: Event? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentEventBinding.inflate(inflater, container, false).apply {
        _binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setListeners()
        setupFragmentEventHeight()
    }

    private fun setupFragmentEventHeight() {
        (dialog as BottomSheetDialog).behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    private fun setListeners() {
        binding.buttonClose.setOnClickListener {
            dismiss()
        }
    }

    private fun setupView() {
        binding.apply {
            textViewEventName.text = event?.name
            textViewEventDescription.text = event?.description
            textViewNameOrganizer.text = event?.user?.name
            textViewConfirmedParticipants.text = event?.numParticipants.toString()
            textViewDate.text = event?.date
            textViewStartTime.text = event?.startTime
            textViewDuration.text = event?.endTime  //TODO calcular duração
            textViewCategory.text = event?.activity?.title
            textViewModality.text = event?.isOnline.toString()
            textViewAcessible.text = event?.accessibilities.toString() //TODO tratar para pegar as acessibilidades
            textViewPets.text = event?.isPetFriendly.toString() //TODO tratar para retornar o texto Sim ou não
            textViewLocalEvent.text = event?.address?.street
            textViewEventFreeOrEventPromoted.text = event?.price.toString()  //TODO tratar para pegar se for pago ou gratuito
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding
    }

    companion object {
        fun newInstance(event: Event?): EventFragment {
            return EventFragment().apply {
                this.event = event
            }
        }
    }
}