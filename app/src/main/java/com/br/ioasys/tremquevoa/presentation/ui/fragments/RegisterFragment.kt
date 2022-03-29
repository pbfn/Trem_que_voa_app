package com.br.ioasys.tremquevoa.presentation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.br.ioasys.tremquevoa.databinding.FragmentRegisterBinding
import com.br.ioasys.tremquevoa.presentation.viewmodel.RegisterViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel


class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding: FragmentRegisterBinding get() = _binding!!
    private val registerViewModel: RegisterViewModel by lazy {
        getViewModel()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentRegisterBinding.inflate(inflater, container, false).apply {
        _binding = this
    }.root

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
    }

    private fun setListeners() {
        binding.apply {
            btnRegister.setOnClickListener {
                registerUser()
            }
        }
    }

    private fun registerUser() {
        registerViewModel.registerUser(
            firstName = binding.editTextFirstName.text.toString(),
            lastName = binding.editTextLastName.text.toString(),
            email = binding.editTextEmail.text.toString(),
            password = binding.editTextPassword.text.toString(),
            passwordConfirmation = binding.editTextConfirmPassword.text.toString()
        )
    }
}