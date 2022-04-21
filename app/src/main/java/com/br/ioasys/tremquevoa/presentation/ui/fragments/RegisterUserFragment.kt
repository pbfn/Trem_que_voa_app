package com.br.ioasys.tremquevoa.presentation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.br.ioasys.tremquevoa.databinding.FragmentRegisterUserBinding
import com.br.ioasys.tremquevoa.domain.exceptions.*
import com.br.ioasys.tremquevoa.extensions.ChangeBackground
import com.br.ioasys.tremquevoa.presentation.viewmodel.RegisterUserViewModel
import com.br.ioasys.tremquevoa.util.ViewState
import com.google.android.material.textfield.TextInputLayout
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
                if (termsIsChecked()) {
                    registerUser()
                }
            }
            textViewSubscribe.setOnClickListener {
                nextPage(RegisterUserFragmentDirections.actionRegisterFragmentToLoginFragment())
            }

            editTextPassword.inputLayout.endIconMode = TextInputLayout.END_ICON_PASSWORD_TOGGLE
            editTextConfirmPassword.inputLayout.endIconMode = TextInputLayout.END_ICON_PASSWORD_TOGGLE

            editTextEmail.input.doAfterTextChanged {
                editTextEmail.ChangeBackground(false, null)
            }
            editTextFirstName.input.doAfterTextChanged {
                editTextFirstName.ChangeBackground(false, null)
            }
            editTextPassword.input.doAfterTextChanged {
                editTextPassword.ChangeBackground(false, null)
            }
            editTextConfirmPassword.input.doAfterTextChanged {
                editTextConfirmPassword.ChangeBackground(false, null)
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
                    nextPage(
                        RegisterUserFragmentDirections.actionRegisterFragmentToWelcomeFragment(
                            response.data.email,
                            binding.editTextPassword.input.text.toString()
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

    private fun termsIsChecked(): Boolean {
        return if (!binding.checkboxTerms.isChecked) {
            binding.textViewErrorMsg.visibility = View.VISIBLE
            false
        } else {
            binding.textViewErrorMsg.visibility = View.GONE
            true
        }
    }

    private fun registerUser() {
        registerViewModel.registerUser(
            firstName = binding.editTextFirstName.input.text.toString(),
            email = binding.editTextEmail.input.text.toString(),
            password = binding.editTextPassword.input.text.toString(),
            passwordConfirmation = binding.editTextConfirmPassword.input.text.toString()
        )
    }

    private fun nextPage(directions: NavDirections) {
        findNavController().navigate(directions)
    }
}