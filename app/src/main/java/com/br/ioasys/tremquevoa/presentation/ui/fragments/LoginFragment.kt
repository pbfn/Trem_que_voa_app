package com.br.ioasys.tremquevoa.presentation.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.br.ioasys.tremquevoa.databinding.FragmentLoginBinding
import com.br.ioasys.tremquevoa.presentation.viewmodel.LoginViewModel
import com.br.ioasys.tremquevoa.util.ViewState
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
        setListeners()
        addObserver()
    }

    private fun setListeners() {
        binding.apply {
            btnLogin.setOnClickListener {
                checkInput(
                    email = editTextEmail.text.toString(),
                    password = editTextPassword.text.toString()
                )
            }

            btnRegister.setOnClickListener {
                nextPage(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
            }
        }
    }

    private fun checkInput(email: String?, password: String?) {
        when {
            email.isNullOrBlank() && password.isNullOrBlank() -> {
                Log.d("LoginFragmentTeste", "Os dois estão vazios")
            }
            email.isNullOrBlank() -> {
                Log.d("LoginFragmentTeste", "Email vazio")
            }
            password.isNullOrBlank() -> {
                Log.d("LoginFragmentTeste", "Senha Vazia")
            }
            else -> {
                Log.d("LoginFragmentTeste", "Ok, pode logar")
                doLogin(
                    email = email,
                    password = password
                )
            }
        }
    }

    private fun doLogin(email: String, password: String) {
        loginViewModel.doLogin(
            email = email,
            password = password
        )
    }

    private fun addObserver() {
        loginViewModel.user.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ViewState.Loading -> {
                    Log.d("LoginFragmentTeste", "Carregando Login")
                }
                is ViewState.Success -> {

                }
                is ViewState.Error -> {

                }
            }
        }
    }

    private fun nextPage(directions: NavDirections) {
        findNavController().navigate(directions)
    }
}