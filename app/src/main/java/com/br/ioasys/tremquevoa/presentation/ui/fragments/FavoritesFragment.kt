package com.br.ioasys.tremquevoa.presentation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.br.ioasys.tremquevoa.databinding.FragmentFavoritesBinding
import com.br.ioasys.tremquevoa.domain.model.Event
import com.br.ioasys.tremquevoa.presentation.adapters.AdapterEvents
import com.br.ioasys.tremquevoa.presentation.adapters.EventClickListener
import com.br.ioasys.tremquevoa.presentation.viewmodel.FavoritiesEventsViewModel
import com.br.ioasys.tremquevoa.util.ViewState
import org.koin.androidx.viewmodel.ext.android.getViewModel

class FavoritesFragment : Fragment(), EventClickListener {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding: FragmentFavoritesBinding get() = _binding!!

    private val favoritiesEventsViewModel: FavoritiesEventsViewModel by lazy {
        getViewModel()
    }

    private var adapterFavoritiesEvents: AdapterEvents? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentFavoritesBinding.inflate(inflater, container, false).apply {
        _binding = this
    }.root

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerViewFavoritesEvents()
        addObserver()
    }

    private fun addObserver() {
        favoritiesEventsViewModel.events.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ViewState.Loading -> {
                }

                is ViewState.Success -> {
                    adapterFavoritiesEvents?.submitList(response.data)
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

    private fun setRecyclerViewFavoritesEvents() {
        adapterFavoritiesEvents = AdapterEvents(this)
        binding.recyclerViewFavoritiesEvents.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = adapterFavoritiesEvents
        }
    }

    override fun onEventClickListener(event: Event) {
        EventFragment.newInstance(event).show(childFragmentManager, "event")
    }
}