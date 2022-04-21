package com.br.ioasys.tremquevoa.presentation.ui.fragments

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doBeforeTextChanged
import androidx.navigation.NavDirections
import androidx.navigation.R
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.br.ioasys.tremquevoa.databinding.FragmentPerfilUserBinding
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
    private lateinit var user: User

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
                subtitle.text = "Nível 2"
            }
            cardMinutes.apply {
                title.text = "140"
                subtitle.text = "Minutos"
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
//            buttonEditAboutMe.setOnClickListener {
//                editAboutMe.isEnabled = true
//            }

//            editAboutMe.doAfterTextChanged {
//                perfilViewModel.updateAboutMe(token = user.token, aboutMe = it.toString())
//            }
//            buttonSettings.setOnClickListener {
//                //findNavController().navigate(PerfilUserFragmentDirections.actionPerfilUserFragmentToSettingsFragment())
//            }
        }
    }

    private fun addObserver() {
        perfilViewModel.interests.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ViewState.Loading -> {

                }
                is ViewState.Success -> {
                    listInterests = response.data
                    adapterInterests.differ.submitList(listInterests)
                }

                is ViewState.Error -> {

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

                }
                else -> {

                }
            }

        }
    }

    private fun setLayout() {
        binding.apply {
            if (!user.aboutMe.isNullOrEmpty()) {
                editAboutMe.text = Editable.Factory.getInstance().newEditable(user.aboutMe)
            }
            textViewName.text = user.name
        }
    }


}