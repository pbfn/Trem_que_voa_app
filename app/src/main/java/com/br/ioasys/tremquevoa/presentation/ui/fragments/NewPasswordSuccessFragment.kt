package com.br.ioasys.tremquevoa.presentation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.br.ioasys.tremquevoa.R
import com.br.ioasys.tremquevoa.databinding.FragmentNewPasswordSuccessBinding

class NewPasswordSuccessFragment : Fragment() {


    private var _binding: FragmentNewPasswordSuccessBinding? = null
    private val binding: FragmentNewPasswordSuccessBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentNewPasswordSuccessBinding.inflate(inflater, container, false).apply {
        _binding = this
    }.root

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListenres()
    }

    private fun setListenres() {
        binding.buttonGoToLogin.setOnClickListener {
            nextPage(NewPasswordSuccessFragmentDirections.actionNewPasswordSuccessFragmentToLoginFragment())
        }
    }

    private fun nextPage(directions: NavDirections) {
        findNavController().navigate(directions)
    }

}