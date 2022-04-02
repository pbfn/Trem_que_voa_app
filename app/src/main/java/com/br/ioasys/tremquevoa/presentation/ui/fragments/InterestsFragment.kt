package com.br.ioasys.tremquevoa.presentation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import androidx.recyclerview.widget.GridLayoutManager
import com.br.ioasys.tremquevoa.R
import com.br.ioasys.tremquevoa.databinding.FragmentInterestsBinding
import com.br.ioasys.tremquevoa.presentation.adapters.AdapterInterests
import com.br.ioasys.tremquevoa.util.MockInteresses

class InterestsFragment : Fragment() {

    private var _binding: FragmentInterestsBinding? = null
    private val binding: FragmentInterestsBinding get() = _binding!!


    private lateinit var adapterInterests: AdapterInterests

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentInterestsBinding.inflate(layoutInflater, container, false).apply {
        _binding = this
    }.root

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        adapterInterests.differ.submitList(MockInteresses.listaInteresses)
    }

    private fun setRecyclerView() {
        val layout = GridLayoutManager(requireContext(), 3, GridLayoutManager.VERTICAL, false)
        adapterInterests = AdapterInterests()
        binding.recyclerViewInterests.apply {
            adapter = adapterInterests
            layoutManager = layout
        }
    }


}