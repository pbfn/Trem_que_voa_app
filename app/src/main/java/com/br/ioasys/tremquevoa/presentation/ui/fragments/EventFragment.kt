package com.br.ioasys.tremquevoa.presentation.ui.fragments

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.br.ioasys.tremquevoa.R
import com.br.ioasys.tremquevoa.databinding.FragmentEventBinding
import com.br.ioasys.tremquevoa.domain.exceptions.RequestException
import com.br.ioasys.tremquevoa.domain.model.Event
import com.br.ioasys.tremquevoa.extensions.*
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

        registerParticipateViewModel.favoriteEvent.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ViewState.Success -> {
                    binding.iconFavorite.setImageDrawable(event?.getIconFavorite(requireContext()))
                }

                is ViewState.Error -> {
                    when (response.throwable) {
                        is RequestException -> {
                            Toast.makeText(
                                requireContext(),
                                getString(R.string.failed_request),
                                Toast.LENGTH_LONG
                            ).show()
                        }
                        else -> {
                            Toast.makeText(
                                requireContext(),
                                getString(R.string.failed_favorite),
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
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

            iconFavorite.setOnClickListener {
                favoriteEvent()
            }
        }
    }

    private fun registerParticipateEvent() {
        registerParticipateViewModel.registerParticipateEvent(
            eventId = event?.id ?: ""
        )
    }

    private fun favoriteEvent() {
        registerParticipateViewModel.favoriteEvent(
            eventId = event?.id ?: ""
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
            iconFavorite.setImageDrawable(event?.getIconFavorite(requireContext()))
            textViewDuration.text = event?.startTime?.differTime(event?.endTime ?: "0")
            textViewCategory.text = event?.activity?.title
            textViewModality.text = getModality(event?.isOnline ?: false)
            textViewAcessible.text =
                event?.accessibilities?.map {
                    it.name
                }?.joinToString(
                    prefix = "",
                    separator = ", ",
                    postfix = ""
                )
            textViewPets.text = getPetFriendly(event?.isPetFriendly ?: false)
            textViewLocalEvent.text = event?.address?.street
            textViewEventFree.text = getTextEventPaymentType(getEventIsFree(event?.price))
            buttonPay.show(getEventIsFree(event?.price).not())
            imageEvent.setImageDrawable(event?.interestImageDrawable(requireContext()))
        }
    }

    private fun getTextEventPaymentType(isFree: Boolean): String {
        return if (isFree) getString(R.string.free_event)
        else event?.price?.toMoney() ?: "R$0,00"
    }

    private fun getEventIsFree(price: Double?): Boolean {
        return price == 0.0
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