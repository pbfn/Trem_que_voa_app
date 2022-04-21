package com.br.ioasys.tremquevoa.presentation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.br.ioasys.tremquevoa.R
import com.br.ioasys.tremquevoa.databinding.FragmentForgotPasswordBinding
import com.br.ioasys.tremquevoa.presentation.viewmodel.ForgotPasswordViewModel
import com.br.ioasys.tremquevoa.util.ViewState
import org.koin.androidx.viewmodel.ext.android.getViewModel


class ForgotPasswordFragment : Fragment() {


    private var _binding: FragmentForgotPasswordBinding? = null
    private val binding: FragmentForgotPasswordBinding get() = _binding!!
    private val forgotPasswordViewModel: ForgotPasswordViewModel by lazy {
        getViewModel()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentForgotPasswordBinding.inflate(inflater, container, false).apply {
        _binding = this
    }.root

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
        setListenres()
    }

    private fun setListenres() {
        binding.apply {
            buttonSendEmail.setOnClickListener {
                resetPassword(email = editTextEmail.input.text.toString())
            }
            btnGotoLogin.setOnClickListener {
                nextPage(ForgotPasswordFragmentDirections.actionForgotPasswordFragmentToLoginFragment())
            }
        }
    }

    private fun nextPage(directions: NavDirections) {
        findNavController().navigate(directions)
    }

    private fun observeData() {
        forgotPasswordViewModel.resetPassword.observe(viewLifecycleOwner) { response ->

            when (response) {

                is ViewState.Loading -> {

                }
                is ViewState.Success -> {
                    nextPage(ForgotPasswordFragmentDirections.actionForgotPasswordFragmentToNewPasswordSuccessFragment())
                }

                is ViewState.Error -> {

                }
            }

        }
    }

    private fun resetPassword(email: String) {
        forgotPasswordViewModel.resetPassword(email = email)
    }
}