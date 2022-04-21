package com.br.ioasys.tremquevoa.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.br.ioasys.tremquevoa.R
import com.br.ioasys.tremquevoa.databinding.FragmentEventBinding
import com.br.ioasys.tremquevoa.domain.model.Event
import com.br.ioasys.tremquevoa.extensions.FORMAT_DATE_VIEW
import com.br.ioasys.tremquevoa.extensions.toString
import com.br.ioasys.tremquevoa.presentation.viewmodel.EventViewModel
import com.br.ioasys.tremquevoa.util.ViewState
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class EventFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentEventBinding? = null
    private val binding: FragmentEventBinding get() = _binding!!

    private var event: Event? = null
    private val registerParticipateViewModel: EventViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentEventBinding.inflate(inflater, container, false).apply {
        _binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerParticipateViewModel.getUserLocal()
        setupView()
        setListeners()
        setupFragmentEventHeight()
        addObserver()
    }

    private fun setupFragmentEventHeight() {
        (dialog as BottomSheetDialog).behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    private fun addObserver() {
        registerParticipateViewModel.eventParticipate.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ViewState.Loading -> {
                }

                is ViewState.Success -> {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.participation_confirmed),
                        Toast.LENGTH_LONG
                    ).show()
                }

                is ViewState.Error -> {
                    Toast.makeText(
                        requireContext(),
                        "Usuário já está participando desse evento",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun setListeners() {
        binding.apply {
            buttonClose.setOnClickListener {
                dismiss()
            }

            buttonParticipate.setOnClickListener {
                registerParticipateEvent()
            }
        }
    }

    private fun registerParticipateEvent() {
        registerParticipateViewModel.registerParticipateEvent(
            eventId = event?.id?:""
        )
    }

    private fun setupView() {
            binding.apply {
                textViewEventName.text = event?.name
                textViewEventDescription.text = event?.description
                textViewNameOrganizer.text = event?.user?.name
                textViewConfirmedParticipants.text = event?.numParticipants.toString()
                textViewDate.text = event?.date?.toString(FORMAT_DATE_VIEW)
                textViewStartTime.text = event?.startTime
                textViewDuration.text = event?.endTime  //TODO calcular duração
                textViewCategory.text = event?.activity?.title
                textViewModality.text = getModality(event?.isOnline ?: false)
                textViewAcessible.text =
                    event?.accessibilities.toString() //TODO tratar para pegar as acessibilidades
                textViewPets.text = getPetFriendly(event?.isPetFriendly ?: false)
                textViewLocalEvent.text = event?.address?.street
                textViewEventFreeOrEventPromoted.text =
                    event?.price.toString()  //TODO tratar para pegar se for pago ou gratuito
            }
        }

        private fun getModality(isOnline: Boolean): String {
            return if (isOnline) getString(R.string.modality_online)
            else getString(R.string.modality_presencial)
        }

        private fun getPetFriendly(isPetFriendly: Boolean): String {
            return if (isPetFriendly) getString(R.string.pet_friendly_yes)
            else getString(R.string.pet_friendly_no)
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