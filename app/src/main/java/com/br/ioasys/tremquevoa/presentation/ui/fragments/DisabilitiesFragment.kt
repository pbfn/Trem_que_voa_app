package com.br.ioasys.tremquevoa.presentation.ui.fragments

import android.os.Bundle
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.br.ioasys.tremquevoa.R
import com.br.ioasys.tremquevoa.databinding.FragmentDisabilitiesBinding
import com.br.ioasys.tremquevoa.domain.model.Disabilities
import com.br.ioasys.tremquevoa.extensions.show
import com.br.ioasys.tremquevoa.presentation.viewmodel.DisabilitiesViewModel
import com.br.ioasys.tremquevoa.util.ViewState
import com.google.android.material.chip.Chip
import org.koin.androidx.viewmodel.ext.android.getViewModel

class DisabilitiesFragment : Fragment() {

    private var _binding: FragmentDisabilitiesBinding? = null
    private val binding: FragmentDisabilitiesBinding get() = _binding!!
    private val selectedIdDisabilitiessList: MutableList<String> = mutableListOf()

    private val disabilitiesViewModel: DisabilitiesViewModel by lazy {
        getViewModel()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentDisabilitiesBinding.inflate(inflater, container, false).apply {
        _binding = this
    }.root

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
        getDisabilities()
        setupListeners()
    }


    private fun getDisabilities() {
        disabilitiesViewModel.getDisabilities()
    }

    private fun setupChips(disabilities: List<Disabilities>) {
        for (disabilitie in disabilities) {
            val chip = Chip(requireContext())
            chip.text = disabilitie.name
            chip.chipStrokeWidth = if (chip.isChecked) 0.0F else TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                2f,
                resources.displayMetrics
            )
            chip.textSize = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP,
                16f,
                resources.displayMetrics
            )
            chip.chipStrokeColor =
                ContextCompat.getColorStateList(requireContext(), R.color.chip_stroke_color)
            chip.isCheckedIconVisible = false
            chip.isCheckable = true
            chip.chipBackgroundColor =
                ContextCompat.getColorStateList(requireContext(), R.color.chip_color_selector)
            chip.setTextColor(
                ContextCompat.getColorStateList(
                    requireContext(),
                    R.color.chip_text_color
                )
            )
            chip.setOnCheckedChangeListener { _, _ ->
                selectedIdDisabilitiessList.add(disabilitie.id)
            }
            binding.chipGroup.addView(chip)
        }
    }

    private fun observeData() {
        disabilitiesViewModel.disabilities.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ViewState.Success -> {
                    setupChips(response.data)
                }
                is ViewState.Error -> {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.failed_request),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
        disabilitiesViewModel.responseSaveDisabilities.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ViewState.Success -> {
                    nextPage(DisabilitiesFragmentDirections.actionDisabilitiesFragmentToCityFragment())
                }
                is ViewState.Error -> {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.failed_request),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

        disabilitiesViewModel.showProgressBar.observe(viewLifecycleOwner) { showProgressBar ->
            binding.progressBar.show(showProgressBar)
        }
    }

    private fun setupListeners() {
        binding.buttonNext.setOnClickListener {
            if (selectedIdDisabilitiessList.size == 0) {
                nextPage(DisabilitiesFragmentDirections.actionDisabilitiesFragmentToCityFragment())
            } else {
                disabilitiesViewModel.saveDisabilitiesByUser(
                    listIdDisabilities = selectedIdDisabilitiessList
                )
            }
        }
        binding.textViewButtonJump.setOnClickListener {
            nextPage(DisabilitiesFragmentDirections.actionDisabilitiesFragmentToCityFragment())
        }
    }

    private fun nextPage(directions: NavDirections) {
        findNavController().navigate(directions)
    }
}