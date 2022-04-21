package com.br.ioasys.tremquevoa.presentation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavArgs
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.br.ioasys.tremquevoa.databinding.FragmentEmergencyContactBinding
import com.br.ioasys.tremquevoa.extensions.ChangeBackground
import com.br.ioasys.tremquevoa.presentation.viewmodel.UpdateUserViewModel
import com.br.ioasys.tremquevoa.util.ViewState
import org.koin.androidx.viewmodel.ext.android.getViewModel


class  EmergencyContactFragment : Fragment() {

    private var _binding: FragmentEmergencyContactBinding? = null
    private val binding: FragmentEmergencyContactBinding get() = _binding!!
    private val updateUserViewModel: UpdateUserViewModel by lazy {
        getViewModel()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentEmergencyContactBinding.inflate(inflater, container, false).apply {
        _binding = this
    }.root

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        addObserver()
    }

    private fun setListeners() {
        binding.apply {
            buttonNext.setOnClickListener {
                updateEmergencyContacts(
                    emergencyName = editTextNamePerson.input.text.toString(),
                    emergencyPhone = editTextNumberPerson.input.text.toString()
                )
            }
            textViewButtonJump.setOnClickListener {
                nextPage(
                    EmergencyContactFragmentDirections.actionEmergencyContactFragmentToInterestsFragment()
                )
            }
        }
    }

    private fun updateEmergencyContacts(emergencyName: String, emergencyPhone: String) {
        updateUserViewModel.updateEmergencyContact(
            emergencyName = emergencyName,
            emergencyPhone = emergencyPhone
        )
    }

    private fun addObserver() {
        updateUserViewModel.responseUpdateEmergencyContact.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ViewState.Loading -> {

                }

                is ViewState.Success -> {
                    nextPage(
                        EmergencyContactFragmentDirections.actionEmergencyContactFragmentToInterestsFragment()
                    )
                }

                is ViewState.Error -> {

                }

            }
        }
        binding.apply {
            editTextNamePerson.ChangeBackground(false, null)
            editTextNumberPerson.ChangeBackground(false, null)
        }
    }

    private fun nextPage(directions: NavDirections) {
        findNavController().navigate(directions)
    }

}