package com.br.ioasys.tremquevoa.presentation.ui.fragments

import android.content.Context
import android.graphics.Paint
import android.graphics.Typeface
import android.os.Bundle
import android.text.Editable
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.TextWatcher
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.br.ioasys.tremquevoa.R
import com.br.ioasys.tremquevoa.databinding.FragmentLoginBinding
import com.br.ioasys.tremquevoa.domain.exceptions.*
import com.br.ioasys.tremquevoa.extensions.ChangeBackground
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
        underlineText()
        setListeners()
        addObserver()
    }

    private fun setListeners() {
        binding.apply {
            btnLogin.setOnClickListener {
                doLogin(
                    email = customEmail.input.text.toString(),
                    password = customPassword.input.text.toString()
                )
            }

            btnRegister.setOnClickListener {
                nextPage(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
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
                }

                is ViewState.Error -> {
                    var msg = ""
                    when (response.throwable) {
                        is InvalidEmptyPasswordException -> {
                            msg = "Por favor informe a senha"
                            binding.customPassword.ChangeBackground(true,msg)
                        }
                        is InvalidEmptyEmailException -> {
                            msg = "Por favor informe o email"
                            binding.customEmail.ChangeBackground(true,msg)
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
                            msg = "Não existe esse email cadastrado"
                            emitError(msg)
                        }
                    }

                }
            }
        }
    }

    private fun nextPage(directions: NavDirections) {
        findNavController().navigate(directions)
    }

    private fun underlineText() {
        binding.btnRegister.paintFlags = Paint.UNDERLINE_TEXT_FLAG
    }

    private fun emitError(msg:String){
        Toast.makeText(
            requireContext(),
            msg,
            Toast.LENGTH_SHORT
        ).show()
        binding.apply {
            customPassword.ChangeBackground(true,null)
            customEmail.ChangeBackground(true,null)
        }
    }

}