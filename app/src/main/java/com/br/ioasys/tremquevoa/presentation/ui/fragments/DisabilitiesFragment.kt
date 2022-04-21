package com.br.ioasys.tremquevoa.presentation.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.br.ioasys.tremquevoa.databinding.FragmentDisabilitiesBinding
import com.br.ioasys.tremquevoa.presentation.adapters.AdapterDisabilities
import com.br.ioasys.tremquevoa.presentation.viewmodel.DisabilitiesViewModel
import com.br.ioasys.tremquevoa.util.ViewState
import org.koin.androidx.viewmodel.ext.android.getViewModel

class DisabilitiesFragment : Fragment() {

    private var _binding: FragmentDisabilitiesBinding? = null
    private val binding: FragmentDisabilitiesBinding get() = _binding!!


    private val disabilitiesViewModel: DisabilitiesViewModel by lazy {
        getViewModel()
    }

    private lateinit var adapterDisabilities: AdapterDisabilities


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
        setupRecyclesView()
        setupListeners()
    }

    private fun setupRecyclesView() {
        val layout = GridLayoutManager(requireContext(), 3, GridLayoutManager.VERTICAL, false)
        adapterDisabilities = AdapterDisabilities()
        binding.recyclerViewDisabilities.apply {
            adapter = adapterDisabilities
            layoutManager = layout
        }
    }

    private fun getDisabilities() {
        disabilitiesViewModel.getDisabilities()
    }

    private fun observeData() {
        disabilitiesViewModel.disabilities.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ViewState.Success -> {
                    adapterDisabilities.differ.submitList(response.data)
                }
            }
        }
        disabilitiesViewModel.responseSaveDisabilities.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ViewState.Success -> {

                }
            }
        }
    }

    private fun setupListeners() {
        binding.buttonNext.setOnClickListener {
            val listIds: MutableList<String> = mutableListOf()
            for (disabilities in adapterDisabilities.listDisabilitiesSelected) {
                listIds.add(disabilities.id)
            }
            disabilitiesViewModel.saveDisabilitiesByUser(
                listIdDisabilities = listIds
            )
        }
    }

    private fun nextPage(directions: NavDirections) {
        findNavController().navigate(directions)
    }
}