package com.br.ioasys.tremquevoa.presentation.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.br.ioasys.tremquevoa.databinding.FragmentRegisterUserBinding
import com.br.ioasys.tremquevoa.presentation.viewmodel.RegisterViewModel
import com.br.ioasys.tremquevoa.util.ViewState
import org.koin.androidx.viewmodel.ext.android.getViewModel


class RegisterUserFragment : Fragment() {

    private var _binding: FragmentRegisterUserBinding? = null
    private val binding: FragmentRegisterUserBinding get() = _binding!!
    private val registerViewModel: RegisterViewModel by lazy {
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

                }
                is ViewState.Success -> {
                    Toast.makeText(
                        requireContext(),
                        "Cadastro realizado com sucesso",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is ViewState.Error -> {
                    Toast.makeText(
                        requireContext(),
                        "Houve uma falha no cadastro",
                        Toast.LENGTH_SHORT
                    ).show()
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