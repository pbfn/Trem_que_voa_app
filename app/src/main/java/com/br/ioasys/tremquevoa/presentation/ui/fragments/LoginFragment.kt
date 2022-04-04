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
        addChangeListener(requireContext())
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
                    changeBackgroundPassword(false,null)
                    changeBackgroundEmail(false,null)
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
                            changeBackgroundPassword(true, msg)
                        }
                        is InvalidEmptyEmailException -> {
                            msg = "Por favor informe o email"
                            changeBackgroundEmail(true,msg)
                        }
                        is InvalidUserException->{
                            msg = "Email ou senha informados errado"
                        }
                        is InvalidPasswordException ->{
                            msg = "Senha errada"
                        }
                        is InvalidEmailExecption ->{
                            msg = "Não existe esse email cadastrado"
                        }
                    }
                    Toast.makeText(
                        requireContext(),
                        msg,
                        Toast.LENGTH_SHORT
                    ).show()
                    changeBackgroundPassword(true,null)
                    changeBackgroundEmail(true,null)
                }
            }
        }
    }

    private fun nextPage(directions: NavDirections) {
        findNavController().navigate(directions)
    }

    private fun underlineText(){
        binding.btnRegister.paintFlags = Paint.UNDERLINE_TEXT_FLAG
    }

    private fun changeBackgroundPassword(error: Boolean,msg:String?) {
        if (error) {
            binding.customPassword.apply {
                msgError.visibility = View.VISIBLE
                input.background = ContextCompat.getDrawable(context, R.drawable.input_custom_error)
                input.setTextColor(ContextCompat.getColor(context,R.color.error))
                msgError.text = msg
            }
        } else {
            binding.customPassword.apply {
                msgError.visibility = View.INVISIBLE
                input.background =
                    ContextCompat.getDrawable(context, R.drawable.input_custom_neutral)
                input.setTextColor(ContextCompat.getColor(context,R.color.neutral))
            }
        }

    }

    private fun changeBackgroundEmail(error: Boolean,msg:String?) {
        if (error) {
            binding.customEmail.apply {
                msgError.visibility = View.VISIBLE
                input.background = ContextCompat.getDrawable(context, R.drawable.input_custom_error)
                input.setTextColor(ContextCompat.getColor(context,R.color.error))
                msgError.text = msg
            }
        } else {
            binding.customPassword.apply {
                msgError.visibility = View.INVISIBLE
                input.background =
                    ContextCompat.getDrawable(context, R.drawable.input_custom_neutral)
                input.setTextColor(ContextCompat.getColor(context,R.color.neutral))
            }
        }

    }

    private fun addChangeListener(context:Context){
        binding.customEmail.input.addTextChangedListener (object : TextWatcher{

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(p1>0){
                    binding.customEmail.input.background =  ContextCompat.getDrawable(context, R.drawable.input_custom_success)
                    binding.customEmail.input.setTextColor(ContextCompat.getColor(context,R.color.neutral))
                }else{
                    binding.customEmail.input.background =  ContextCompat.getDrawable(context, R.drawable.input_custom_neutral)
                    binding.customEmail.input.setTextColor(ContextCompat.getColor(context,R.color.neutral))
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })

        binding.customPassword.input.addTextChangedListener (object : TextWatcher{

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(p1>0){
                    binding.customPassword.input.background =  ContextCompat.getDrawable(context, R.drawable.input_custom_success)
                    binding.customPassword.input.setTextColor(ContextCompat.getColor(context,R.color.neutral))
                }else{
                    binding.customPassword.input.background =  ContextCompat.getDrawable(context, R.drawable.input_custom_neutral)
                    binding.customPassword.input.setTextColor(ContextCompat.getColor(context,R.color.neutral))
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
    }
}