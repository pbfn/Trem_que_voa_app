package com.br.ioasys.tremquevoa.presentation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.br.ioasys.tremquevoa.databinding.FragmentRegisterUserBinding
import com.br.ioasys.tremquevoa.domain.exceptions.*
import com.br.ioasys.tremquevoa.extensions.ChangeBackground
import com.br.ioasys.tremquevoa.presentation.viewmodel.RegisterUserViewModel
import com.br.ioasys.tremquevoa.util.ViewState
import org.koin.androidx.viewmodel.ext.android.getViewModel


class RegisterUserFragment : Fragment() {

    private var _binding: FragmentRegisterUserBinding? = null
    private val binding: FragmentRegisterUserBinding get() = _binding!!
    private val registerViewModel: RegisterUserViewModel by lazy {
        getViewModel()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentRegisterUserBinding.inflate(inflater, container, false).apply {
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
            btnRegister.setOnClickListener {
                registerUser()
            }
        }
    }

    private fun addObserver() {
        registerViewModel.user.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ViewState.Loading -> {
                    binding.apply {
                        editTextFirstName.ChangeBackground(false, null)
                        editTextEmail.ChangeBackground(false, null)
                        editTextPassword.ChangeBackground(false, null)
                        editTextConfirmPassword.ChangeBackground(false, null)
                    }
                }
                is ViewState.Success -> {
                    Toast.makeText(
                        requireContext(),
                        "Cadastro realizado com sucesso",
                        Toast.LENGTH_SHORT
                    ).show()
                    findNavController().navigate(
                        RegisterUserFragmentDirections.actionRegisterFragmentToEmergencyContactFragment(
                            response.data.id
                        )
                    )
                }
                is ViewState.Error -> {
                    var msg = ""
                    when (response.throwable) {
                        is InvalidEmptyFirstNameException -> {
                            msg = "Por favor informe o nome"
                            binding.editTextFirstName.ChangeBackground(true, msg)
                        }
                        is InvalidEmptyEmailException -> {
                            msg = "Por favor informe o email"
                            binding.editTextEmail.ChangeBackground(true, msg)
                        }

                        is InvalidEmptyPasswordException -> {
                            msg = "Por favor informe a senha"
                            binding.editTextPassword.ChangeBackground(true, msg)
                        }

                        is InvalidMinimunPassword -> {
                            msg = "A senha não pode ser menor que 6 digitos"
                            binding.editTextPassword.ChangeBackground(true, msg)
                        }

                        is InvalidEmptyPasswordConfirmException -> {
                            msg = "Por favor informe a confirmação de senha"
                            binding.editTextConfirmPassword.ChangeBackground(true, msg)
                        }

                        is InvalidDifferPasswordException -> {
                            msg = "As senhas não estão iguais, por favor informe novamente"
                            binding.editTextConfirmPassword.ChangeBackground(true, msg)
                        }

                        is EmailAlreadyUsed -> {
                            msg = "Email já esta sendo utilizado"
                            binding.editTextEmail.ChangeBackground(true, msg)
                        }
                        is InvalidRegisterException -> {
                            binding.apply {
                                editTextFirstName.ChangeBackground(true, null)
                                editTextEmail.ChangeBackground(true, null)
                                editTextPassword.ChangeBackground(true, null)
                                editTextConfirmPassword.ChangeBackground(true, null)
                            }
                            Toast.makeText(
                                requireContext(),
                                "Houve uma falha no cadastro",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                }
            }
        }
    }

    private fun registerUser() {
        registerViewModel.registerUser(
            firstName = binding.editTextFirstName.input.text.toString(),
            lastName = "pedro",
            email = binding.editTextEmail.input.text.toString(),
            password = binding.editTextPassword.input.text.toString(),
            passwordConfirmation = binding.editTextConfirmPassword.input.text.toString()
        )
    }
}