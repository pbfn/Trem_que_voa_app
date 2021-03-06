package com.br.ioasys.tremquevoa.presentation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.br.ioasys.tremquevoa.R
import com.br.ioasys.tremquevoa.databinding.FragmentInterestsBinding
import com.br.ioasys.tremquevoa.domain.exceptions.RequestException
import com.br.ioasys.tremquevoa.domain.model.Interests
import com.br.ioasys.tremquevoa.extensions.show
import com.br.ioasys.tremquevoa.presentation.adapters.AdapterInterests
import com.br.ioasys.tremquevoa.presentation.viewmodel.InterestsViewModel
import com.br.ioasys.tremquevoa.util.ViewState
import org.koin.androidx.viewmodel.ext.android.getViewModel

class InterestsFragment : Fragment() {

    private var _binding: FragmentInterestsBinding? = null
    private val binding: FragmentInterestsBinding get() = _binding!!
    private val interestsViewModel: InterestsViewModel by lazy {
        getViewModel()
    }

    private var listInterests: List<Interests> = listOf()

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
        addObserver()
        setListeners()
    }

    private fun setRecyclerView() {
        val layout = GridLayoutManager(requireContext(), 4, GridLayoutManager.VERTICAL, false)
        adapterInterests = AdapterInterests()
        binding.recyclerViewInterests.apply {
            adapter = adapterInterests
            layoutManager = layout
        }
    }

    private fun addObserver() {
        interestsViewModel.interests.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ViewState.Loading -> {

                }
                is ViewState.Success -> {
                    listInterests = response.data
                    adapterInterests.differ.submitList(listInterests)
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
                                getString(R.string.failed_request),
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }
                else -> {

                }
            }

        }

        interestsViewModel.saveInterests.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ViewState.Loading -> {

                }
                is ViewState.Success -> {
                    nextPage(
                        InterestsFragmentDirections.actionInterestsFragmentToDisabilitiesFragment()
                    )
                }

                is ViewState.Error -> {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.failed_request),
                        Toast.LENGTH_LONG
                    ).show()
                }
                else -> {

                }
            }

        }

        interestsViewModel.showProgressBar.observe(viewLifecycleOwner) { showProgressBar ->
            binding.progressBar.show(showProgressBar)
        }
    }

    private fun setListeners() {
        binding.apply {
            buttonNext.setOnClickListener {
                val selectedIdInterestsList: MutableList<String> = mutableListOf()
                listInterests.forEach { interest ->
                    if (interest.selected) {
                        selectedIdInterestsList.add(interest.id)
                    }
                }
                if (selectedIdInterestsList.size == 0) {
                    nextPage(InterestsFragmentDirections.actionInterestsFragmentToDisabilitiesFragment())
                } else {
                    interestsViewModel.saveInterestsByUser(selectedIdInterestsList)
                }
            }
            textViewButtonJump.setOnClickListener {
                nextPage(InterestsFragmentDirections.actionInterestsFragmentToDisabilitiesFragment())
            }
        }

    }

    private fun nextPage(directions: NavDirections) {
        findNavController().navigate(directions)
    }
}