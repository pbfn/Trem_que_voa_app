package com.br.ioasys.tremquevoa.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.br.ioasys.tremquevoa.R
import com.br.ioasys.tremquevoa.databinding.FragmentLoginBinding
import com.br.ioasys.tremquevoa.domain.exceptions.*
import com.br.ioasys.tremquevoa.extensions.ChangeBackground
import com.br.ioasys.tremquevoa.extensions.show
import com.br.ioasys.tremquevoa.extensions.showComingSoon
import com.br.ioasys.tremquevoa.presentation.viewmodel.LoginViewModel
import com.br.ioasys.tremquevoa.util.ViewState
import com.google.android.material.textfield.TextInputLayout
import org.koin.androidx.viewmodel.ext.android.getViewModel


class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding: FragmentLoginBinding get() = _binding!!
    private val loginViewModel: LoginViewModel by lazy {
        getViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentLoginBinding.inflate(inflater, container, false).apply {
        _binding = this
    }.root

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            customEmail.ChangeBackground(false, null)
            customPassword.ChangeBackground(false, null)
        }
        setListeners()
        addObserver()
    }

    private fun setListeners() {
        binding.apply {
            btnLogin.setOnClickListener {
                doLogin(
                    email = customEmail.input.text.toString(),
                    password = customPassword.input.text.toString(),
                    maintainLogin = toggleStayConnected.isChecked
                )
            }
            btnRegisterGoogle.setOnClickListener {
                val toast = Toast(requireContext())
                toast.showComingSoon(requireContext())
            }
            btnRegister.setOnClickListener {
                nextPage(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
            }

            customPassword.inputLayout.endIconMode = TextInputLayout.END_ICON_PASSWORD_TOGGLE

            customPassword.input.doAfterTextChanged {
                customEmail.ChangeBackground(false, null)
                customPassword.ChangeBackground(false, null)
            }

            customEmail.input.doAfterTextChanged {
                customEmail.ChangeBackground(false, null)
                customPassword.ChangeBackground(false, null)
            }


            textViewForgotPassword.setOnClickListener {
                nextPage(LoginFragmentDirections.actionLoginFragmentToForgotPasswordFragment())
            }
        }
    }

    private fun doLogin(email: String, password: String, maintainLogin: Boolean) {
        loginViewModel.doLogin(
            email = email,
            password = password,
        )
    }

    private fun addObserver() {
        loginViewModel.user.observe(viewLifecycleOwner) { response ->
            when (response) {

                is ViewState.Loading -> {
                    binding.apply {
                        customEmail.ChangeBackground(false, null)
                        customPassword.ChangeBackground(false, null)
                    }
                }

                is ViewState.Success -> {
                    Toast.makeText(
                        requireContext(),
                        "Login realizado com sucesso",
                        Toast.LENGTH_SHORT
                    ).show()
                    loginViewModel.setMaintainLogin(binding.toggleStayConnected.isChecked)
                    nextPage(LoginFragmentDirections.actionLoginFragmentToHomeActivity())
                }

                is ViewState.Error -> {
                    var msg = ""
                    when (response.throwable) {
                        is InvalidEmptyPasswordException -> {
                            msg = "Por favor informe a senha"
                            binding.customPassword.ChangeBackground(true, msg)
                        }
                        is InvalidEmptyEmailException -> {
                            msg = "Por favor informe o email"
                            binding.customEmail.ChangeBackground(true, msg)
                        }
                        is InvalidUserException -> {
                            msg = "Email ou senha informados errado"
                            emitError(msg)
                        }
                        is InvalidPasswordException -> {
                            msg = "Senha errada"
                            emitError(msg)
                        }
                        is InvalidEmailExecption -> {
                            msg = "N??o existe esse email cadastrado"
                            emitError(msg)
                        }
                        is RequestException->{
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
            }
        }

        loginViewModel.showProgressBar.observe(viewLifecycleOwner) { showProgressBar ->
            binding.progressBar.show(showProgressBar)
        }
    }

    private fun nextPage(directions: NavDirections) {
        findNavController().navigate(directions)
    }


    private fun emitError(msg: String) {
        Toast.makeText(
            requireContext(),
            msg,
            Toast.LENGTH_SHORT
        ).show()
        binding.apply {
            customPassword.ChangeBackground(true, null)
            customEmail.ChangeBackground(true, null)
        }
    }

}