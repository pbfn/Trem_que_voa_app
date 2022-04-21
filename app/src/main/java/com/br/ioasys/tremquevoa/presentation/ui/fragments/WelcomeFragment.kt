package com.br.ioasys.tremquevoa.presentation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.br.ioasys.tremquevoa.R
import com.br.ioasys.tremquevoa.databinding.FragmentWelcomeBinding
import com.br.ioasys.tremquevoa.domain.exceptions.*
import com.br.ioasys.tremquevoa.extensions.ChangeBackground
import com.br.ioasys.tremquevoa.presentation.viewmodel.LoginViewModel
import com.br.ioasys.tremquevoa.util.ViewState
import org.koin.androidx.viewmodel.ext.android.getViewModel

class WelcomeFragment : Fragment() {


    private var _binding: FragmentWelcomeBinding? = null
    private val binding: FragmentWelcomeBinding get() = _binding!!
    private val loginViewModel: LoginViewModel by lazy {
        getViewModel()
    }

    private val args: WelcomeFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentWelcomeBinding.inflate(inflater, container, false).apply {
        _binding = this
    }.root

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeneres()
        addObserve()
    }

    private fun setListeneres() {
        binding.apply {
            btnNext.setOnClickListener {
                doLogin()
            }
        }
    }

    private fun doLogin() {
        loginViewModel.doLogin(
            email = args.email,
            password = args.password,
            maintainLogin = false
        )
    }

    private fun addObserve() {
        loginViewModel.user.observe(viewLifecycleOwner) { response ->
            when (response) {

                is ViewState.Loading -> {

                }

                is ViewState.Success -> {
                    nextPage(
                        WelcomeFragmentDirections.actionWelcomeFragmentToEmergencyContactFragment()
                    )
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