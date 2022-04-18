package com.br.ioasys.tremquevoa.presentation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.br.ioasys.tremquevoa.databinding.FragmentHomeBinding
import com.br.ioasys.tremquevoa.domain.model.Event
import com.br.ioasys.tremquevoa.domain.model.User
import com.br.ioasys.tremquevoa.presentation.adapters.AdapterDisabilities
import com.br.ioasys.tremquevoa.presentation.adapters.AdapterEvents
import com.br.ioasys.tremquevoa.presentation.adapters.EventClickListener
import com.br.ioasys.tremquevoa.presentation.viewmodel.HomeViewModel
import com.br.ioasys.tremquevoa.util.ViewState
import org.koin.androidx.viewmodel.ext.android.getViewModel

class HomeFragment : Fragment(), EventClickListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding get() = _binding!!

    private var user: User? = null

    private val homeViewModel: HomeViewModel by lazy {
        getViewModel()
    }

    private lateinit var adapterEvents: AdapterEvents

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
        addObserver()
        homeViewModel.getEvent(user?.token?:"")
    }

    private fun addObserver() {
        homeViewModel.user.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ViewState.Loading -> {

                }
                is ViewState.Success -> {
                    user = response.data
                }

                is ViewState.Error -> {

                }
                else -> {

                }
            }

        }

        homeViewModel.events.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ViewState.Loading -> {
                }

                is ViewState.Success -> {
                    adapterEvents.submitList(response.data)
                }

                is ViewState.Error -> {
                    Toast.makeText(
                        requireContext(),
                        "Falha na lista de eventos",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun setRecyclerViewEvents() {
        adapterEvents = AdapterEvents(this)
        binding.recyclerViewEvents.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = adapterEvents
        }
    }

    override fun onEventClickListener(event: Event) {
        EventFragment.newInstance(event).show(childFragmentManager, "event")
    }
}