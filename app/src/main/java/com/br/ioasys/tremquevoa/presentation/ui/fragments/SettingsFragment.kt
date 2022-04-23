package com.br.ioasys.tremquevoa.presentation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.br.ioasys.tremquevoa.databinding.FragmentSettingsBinding
import com.br.ioasys.tremquevoa.extensions.show
import com.br.ioasys.tremquevoa.extensions.showComingSoon
import com.br.ioasys.tremquevoa.presentation.viewmodel.SettingsViewModel
import com.br.ioasys.tremquevoa.util.ViewState
import org.koin.androidx.viewmodel.ext.android.getViewModel


class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding: FragmentSettingsBinding get() = _binding!!

    private val settingsViewModel: SettingsViewModel by lazy {
        getViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentSettingsBinding.inflate(layoutInflater, container, false).apply {
        _binding = this
    }.root

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        obseveData()
    }

    private fun setupListeners() {
        binding.apply {
            btnBack.setOnClickListener {
                activity?.onBackPressed()
            }
            btnEditPerfil.setOnClickListener {
                showComingSoon()
            }
            btnInviteFriends.setOnClickListener {
                showComingSoon()
            }
            btnNotification.setOnClickListener {
                showComingSoon()
            }
            btnSecurity.setOnClickListener {
                showComingSoon()
            }
            btnHelp.setOnClickListener {
                showComingSoon()
            }
            btnLogout.setOnClickListener {
                settingsViewModel.setMaintainLogin()
                settingsViewModel.wipeToken()
            }
        }
    }

    private fun showComingSoon() {
        val toast = Toast(requireContext())
        toast.showComingSoon(requireContext())
    }

    private fun obseveData() {
        settingsViewModel.cleanToken.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ViewState.Success -> {
                    nextPage(SettingsFragmentDirections.actionSettingsFragmentToMainActivity())
                }
            }

        }
        settingsViewModel.showProgressBar.observe(viewLifecycleOwner) { showProgressBar ->
            binding.progressBar.show(showProgressBar)
        }

    }

    private fun nextPage(directions: NavDirections) {
        findNavController().navigate(directions)
    }
}

