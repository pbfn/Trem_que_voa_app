package com.br.ioasys.tremquevoa.presentation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.navigation.NavArgs
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.br.ioasys.tremquevoa.R
import com.br.ioasys.tremquevoa.databinding.FragmentEmergencyContactBinding
import com.br.ioasys.tremquevoa.domain.exceptions.EmpytNameContatct
import com.br.ioasys.tremquevoa.domain.exceptions.EmpytNumberContatct
import com.br.ioasys.tremquevoa.extensions.ChangeBackground
import com.br.ioasys.tremquevoa.extensions.show
import com.br.ioasys.tremquevoa.presentation.viewmodel.UpdateUserViewModel
import com.br.ioasys.tremquevoa.util.ViewState
import org.koin.androidx.viewmodel.ext.android.getViewModel


class EmergencyContactFragment : Fragment() {

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
        binding.apply {
            editTextNamePerson.ChangeBackground(false, null)
            editTextNumberPerson.ChangeBackground(false, null)
        }
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

            editTextNumberPerson.input.doAfterTextChanged {
                editTextNumberPerson.ChangeBackground(false, null)
                editTextNamePerson.ChangeBackground(false, null)
            }

            editTextNamePerson.input.doAfterTextChanged {
                editTextNumberPerson.ChangeBackground(false, null)
                editTextNamePerson.ChangeBackground(false, null)
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
                    var msg = ""
                    when (response.throwable) {
                        is EmpytNameContatct -> {
                            msg = "Por favor informe o nome"
                            binding.editTextNamePerson.ChangeBackground(true, msg)
                        }

                        is EmpytNumberContatct -> {
                            msg = "Por favor informe o número"
                            binding.editTextNumberPerson.ChangeBackground(true, msg)
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

            }
        }
        updateUserViewModel.showProgressBar.observe(viewLifecycleOwner) { showProgressBar ->
            binding.progressBar.show(showProgressBar)
        }
    }

    private fun nextPage(directions: NavDirections) {
        findNavController().navigate(directions)
    }

}