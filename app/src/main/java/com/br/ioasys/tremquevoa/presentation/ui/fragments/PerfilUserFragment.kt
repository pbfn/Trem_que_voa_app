package com.br.ioasys.tremquevoa.presentation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.br.ioasys.tremquevoa.R
import com.br.ioasys.tremquevoa.databinding.FragmentPerfilUserBinding
import com.br.ioasys.tremquevoa.domain.exceptions.RequestException
import com.br.ioasys.tremquevoa.domain.model.Disabilities
import com.br.ioasys.tremquevoa.domain.model.Interests
import com.br.ioasys.tremquevoa.domain.model.User
import com.br.ioasys.tremquevoa.presentation.adapters.AdapterDeficiencyPerfil
import com.br.ioasys.tremquevoa.presentation.adapters.AdapterInterestsPerfil
import com.br.ioasys.tremquevoa.presentation.viewmodel.PerfilViewModel
import com.br.ioasys.tremquevoa.util.ViewState
import org.koin.androidx.viewmodel.ext.android.getViewModel


class PerfilUserFragment : Fragment() {

    private var _binding: FragmentPerfilUserBinding? = null
    private val binding: FragmentPerfilUserBinding get() = _binding!!

    private lateinit var adapterInterests: AdapterInterestsPerfil
    private lateinit var adapterDeficiency: AdapterDeficiencyPerfil

    private val perfilViewModel: PerfilViewModel by lazy {
        getViewModel()
    }
    private var listInterests: List<Interests> = listOf()
    private var listDeficiency: List<Disabilities> = listOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentPerfilUserBinding.inflate(inflater, container, false).apply {
        _binding = this
    }.root

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addObserver()
        setListeners()
        setRecyclerView()
        setCardLayout()
    }

    private fun setCardLayout() {
        binding.apply {
            cardEvents.apply {
                title.text = "2"
                subtitle.text = "Eventos"
            }
            cardNivel.apply {
                title.visibility = View.GONE
                icon.visibility = View.VISIBLE
                subtitle.text = "N??vel 1"
            }
            cardMinutes.apply {
                title.text = "7"
                subtitle.text = "Moods"
            }

        }
    }

    private fun setRecyclerView() {
        val layoutInterests =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        adapterInterests = AdapterInterestsPerfil()
        binding.recyclerViewInterests.apply {
            adapter = adapterInterests
            layoutManager = layoutInterests
        }

        val layoutDeficiency =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        adapterDeficiency = AdapterDeficiencyPerfil()
        binding.recyclerViewDisabilities.apply {
            adapter = adapterDeficiency
            layoutManager = layoutDeficiency
        }
    }

    private fun setListeners() {
        binding.apply {
            btnInsights.setOnClickListener {
                nextPage(PerfilUserFragmentDirections.actionPerfilUserFragmentToWellnessFragment())
            }

            btnSettings.setOnClickListener {
                nextPage(PerfilUserFragmentDirections.actionPerfilUserFragmentToSettingsFragment())
            }
        }
    }

    private fun addObserver() {
        perfilViewModel.interests.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ViewState.Loading -> {

                }
                is ViewState.Success -> {
                    listInterests = response.data
                    if (response.data.isEmpty()) {
                        binding.apply {
                            recyclerViewDisabilities.visibility = View.INVISIBLE
                            itemEmpty.visibility = View.VISIBLE
                        }
                    } else {
                        adapterInterests.differ.submitList(listInterests)
                    }

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
        perfilViewModel.deficiency.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ViewState.Loading -> {

                }
                is ViewState.Success -> {
                    listDeficiency = response.data
                    adapterDeficiency.differ.submitList(listDeficiency)
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

        perfilViewModel.user.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ViewState.Loading -> {

                }
                is ViewState.Success -> {
                    setLayout(user = response.data)
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
    }

    private fun setLayout(user: User) {
        binding.apply {
            textViewName.text = user.name
            textViewAboutMe.text = user.aboutMe
        }
    }

    private fun nextPage(directions: NavDirections) {
        findNavController().navigate(directions)
    }

}