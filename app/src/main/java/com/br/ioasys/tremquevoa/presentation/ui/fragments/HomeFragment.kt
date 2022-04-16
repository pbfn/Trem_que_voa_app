package com.br.ioasys.tremquevoa.presentation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.br.ioasys.tremquevoa.databinding.FragmentHomeBinding
import com.br.ioasys.tremquevoa.presentation.adapters.AdapterDisabilities
import com.br.ioasys.tremquevoa.presentation.adapters.AdapterEvents

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding get() = _binding!!

    private lateinit var adapterEvents: AdapterEvents
    private lateinit var adapterDisabilities: AdapterDisabilities


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentHomeBinding.inflate(layoutInflater, container, false).apply {
        _binding = this
    }.root

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerViewEvents()
    }

    private fun setRecyclerViewEvents() {
        val layout = GridLayoutManager(requireContext(),2, GridLayoutManager.HORIZONTAL, false)
        adapterEvents = AdapterEvents()
        binding.recyclerViewEvents.apply {
            adapter = adapterEvents
            layoutManager = layout
        }
    }
}