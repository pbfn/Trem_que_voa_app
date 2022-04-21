package com.br.ioasys.tremquevoa.presentation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.br.ioasys.tremquevoa.databinding.FragmentCityBinding
import com.br.ioasys.tremquevoa.domain.exceptions.EmptyCity
import com.br.ioasys.tremquevoa.extensions.ChangeBackground
import com.br.ioasys.tremquevoa.presentation.viewmodel.CityViewModel
import com.br.ioasys.tremquevoa.util.ViewState
import org.koin.androidx.viewmodel.ext.android.getViewModel

class CityFragment : Fragment() {

    private var _binding: FragmentCityBinding? = null
    private val binding: FragmentCityBinding get() = _binding!!

    private val cityViewModel: CityViewModel by lazy {
        getViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentCityBinding.inflate(inflater, container, false).apply {
        _binding = this
    }.root

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
        setupListeners()
    }

    private fun observeData() {
        cityViewModel.responseSaveCity.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ViewState.Loading -> {

                }
                is ViewState.Success -> {
                    nextPage(CityFragmentDirections.actionCityFragmentToHomeActivity())
                }

                is ViewState.Error -> {
                    when (response.throwable) {
                        is EmptyCity -> {
                            binding.textInputCity.ChangeBackground(
                                true,
                                "Por favor informe uma cidade"
                            )
                        }
                    }
                }

            }
        }
    }

    private fun setupListeners() {
        binding.apply {
            btnNext.setOnClickListener {
                cityViewModel.saveCity(textInputCity.input.text.toString())
            }
            textInputCity.input.doAfterTextChanged {
                textInputCity.ChangeBackground(false, null)
            }
        }
    }

    private fun nextPage(directions: NavDirections) {
        findNavController().navigate(directions)
    }
}