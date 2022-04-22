package com.br.ioasys.tremquevoa.presentation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.br.ioasys.tremquevoa.databinding.FragmentWellnessBinding
import com.br.ioasys.tremquevoa.presentation.adapters.AdapterWellness
import com.br.ioasys.tremquevoa.presentation.viewmodel.WellnessViewModel
import com.br.ioasys.tremquevoa.util.ViewState
import org.koin.androidx.viewmodel.ext.android.getViewModel

class WellnessFragment : Fragment() {

    private var _binding: FragmentWellnessBinding? = null
    private val binding: FragmentWellnessBinding get() = _binding!!
    private lateinit var adapterWellness: AdapterWellness

    private val wellnessViewModel: WellnessViewModel by lazy {
        getViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentWellnessBinding.inflate(inflater, container, false).apply {
        _binding = this
    }.root

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observaData()
        setupRecyclerView()
        setupListener()
    }

    private fun observaData() {
        wellnessViewModel.wellness.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ViewState.Success -> {
                    adapterWellness.itemLimit = response.data.size
                    adapterWellness.differ.submitList(response.data)
                }
            }
        }
    }

    private fun setupRecyclerView() {
        adapterWellness = AdapterWellness()
        val layout = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
        binding.recyclerViewWellness.apply {
            adapter = adapterWellness
            layoutManager = layout
        }
    }

    private fun setupListener() {
        binding.btnBack.setOnClickListener {
            activity?.onBackPressed()
        }
    }

}