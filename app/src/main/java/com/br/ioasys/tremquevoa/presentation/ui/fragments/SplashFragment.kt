package com.br.ioasys.tremquevoa.presentation.ui.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.br.ioasys.tremquevoa.R
import com.br.ioasys.tremquevoa.databinding.FragmentSplashBinding
import com.br.ioasys.tremquevoa.presentation.viewmodel.SplashViewModel
import com.br.ioasys.tremquevoa.util.ViewState
import org.koin.androidx.viewmodel.ext.android.getViewModel


class SplashFragment : Fragment() {

    private var _binding: FragmentSplashBinding? = null
    private val binding: FragmentSplashBinding get() = _binding!!

    private val splashViewModel: SplashViewModel by lazy {
        getViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentSplashBinding.inflate(inflater, container, false).apply {
        _binding = null
    }.root

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
    }


    private fun observeData() {
        splashViewModel.user.observe(viewLifecycleOwner) { response ->
            when (response) {

                is ViewState.Loading -> {

                }

                is ViewState.Success -> {

                    if (response.data.maintainLogin) {

                        nextPage(SplashFragmentDirections.actionSplashFragmentToHomeActivity())
                    } else {

                        nextPage(SplashFragmentDirections.actionSplashFragmentToLoginFragment())

                    }
                }

                is ViewState.Error -> {
                    nextPage(SplashFragmentDirections.actionSplashFragmentToLoginFragment())
                }

            }
        }
    }

    private fun nextPage(directions: NavDirections) {
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            findNavController().navigate(directions)
        }, 4000)

    }

}