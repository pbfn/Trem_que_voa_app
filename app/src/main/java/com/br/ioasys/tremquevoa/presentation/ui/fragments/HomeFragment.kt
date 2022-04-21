package com.br.ioasys.tremquevoa.presentation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.br.ioasys.tremquevoa.R
import com.br.ioasys.tremquevoa.databinding.FragmentHomeBinding
import com.br.ioasys.tremquevoa.domain.model.Disabilities
import com.br.ioasys.tremquevoa.domain.model.Event
import com.br.ioasys.tremquevoa.domain.model.Interests
import com.br.ioasys.tremquevoa.domain.model.User
import com.br.ioasys.tremquevoa.extensions.ChangeIcon
import com.br.ioasys.tremquevoa.presentation.adapters.*
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

    private lateinit var adapterEventsPromoted: AdapterEvents
    private lateinit var adapterEventsOnline: AdapterEvents
    private lateinit var adapterEventsRecommended: AdapterEvents
    private lateinit var adapterInterests: AdapterInterestsPerfil
    private lateinit var adapterDisabilities: AdapterDisabilities

    private var listInterests: List<Interests> = listOf()
    private var listDisabilities: List<Disabilities> = listOf()

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
        setRecyclerViewDisabilities()
        setRecyclerViewInterest()
        setListener()
        addObserver()
        homeViewModel.getEvent(user?.token ?: "")

    }

    private fun addObserver() {
        homeViewModel.user.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ViewState.Loading -> {

                }
                is ViewState.Success -> {
                    user = response.data
                    configureNameUserTitle(user?.name)
                    homeViewModel.getInterest(user?.token ?: "")
                    homeViewModel.getDisabilities(user?.token ?: "")
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
                    adapterEventsPromoted.submitList(response.data.listPromoted)
                    adapterEventsOnline.submitList(response.data.listOnline)
                    adapterEventsRecommended.submitList(response.data.listRecommended)
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

        homeViewModel.interest.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ViewState.Loading -> {
                }

                is ViewState.Success -> {
                    listInterests = response.data
                    adapterInterests.differ.submitList(listInterests)
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

        homeViewModel.disabilities.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ViewState.Loading -> {
                }

                is ViewState.Success -> {
                    listDisabilities = response.data
                    adapterDisabilities.differ.submitList(listDisabilities)
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

    private fun configureNameUserTitle(name: String?) {
        binding.nameUser.text = String.format(getString(R.string.bem_vindo), name)
    }

    private fun setListener() {
        binding.apply {
            customSearch.ChangeIcon(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_search,
                )!!, {}
            )
        }
    }

    private fun setRecyclerViewEvents() {
        adapterEventsPromoted = AdapterEvents(this)
        binding.recyclerViewPromotedEvents.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = adapterEventsPromoted
        }

        adapterEventsOnline = AdapterEvents(this)
        binding.recyclerViewOnline.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = adapterEventsOnline
        }

        adapterEventsRecommended = AdapterEvents(this)
        binding.recyclerViewRecomended.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = adapterEventsRecommended
        }
    }

    private fun setRecyclerViewInterest() {
        adapterInterests = AdapterInterestsPerfil()
        binding.recyclerViewCategoryHome.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = adapterInterests
        }
    }

    private fun setRecyclerViewDisabilities() {
        adapterDisabilities = AdapterDisabilities()
        binding.recyclerViewAccessibitiesHome.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = adapterDisabilities
        }
    }

    override fun onEventClickListener(event: Event) {
        EventFragment.newInstance(event).show(childFragmentManager, "event")
    }
}