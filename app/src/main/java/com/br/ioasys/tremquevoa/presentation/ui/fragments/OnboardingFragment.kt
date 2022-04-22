package com.br.ioasys.tremquevoa.presentation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.br.ioasys.tremquevoa.R
import com.br.ioasys.tremquevoa.databinding.FragmentOnboardingBinding

class OnboardingFragment : Fragment() {

    private var _binding: FragmentOnboardingBinding? = null
    private val binding: FragmentOnboardingBinding get() = _binding!!

    private var page: Int = 0
    private val args: OnboardingFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentOnboardingBinding.inflate(layoutInflater, container, false).apply {
        _binding = this
    }.root

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListerenrs()
        initView()
    }

    private fun initView() {
        page = args.page
        when (page) {
            2 -> {
                binding.apply {
                    imageView.setImageDrawable(
                        ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.ic_onboard_mid
                        )
                    )
                    textViewTitle.text = getText(R.string.title_onboarding_mid)
                    textViewSubTitle.text = getText(R.string.subtitle_onboarding_mid)
                    imageViewFirst.setImageDrawable(
                        ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.ic_onboarding_off
                        )
                    )
                    imageViewSecond.setImageDrawable(
                        ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.ic_onboarding_on
                        )
                    )
                }
            }
            3 -> {
                binding.apply {
                    imageView.setImageDrawable(
                        ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.ic_onboard_end
                        )
                    )
                    imageViewFirst.setImageDrawable(
                        ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.ic_onboarding_off
                        )
                    )
                    imageViewSecond.setImageDrawable(
                        ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.ic_onboarding_off
                        )
                    )
                    imageViewThird.setImageDrawable(
                        ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.ic_onboarding_on
                        )
                    )

                    textViewTitle.text = getText(R.string.title_onboarding_end)
                    textViewSubTitle.text = getText(R.string.subtitle_onboarding_end)
                    btnNext.setOnClickListener {
                        nextPage(OnboardingFragmentDirections.actionInitOnboardingFragmentToLoginFragment())
                    }
                }
            }
        }
    }

    private fun setListerenrs() {
        binding.btnNext.setOnClickListener {
            nextPage(
                OnboardingFragmentDirections.actionInitOnboardingFragmentSelf(
                    page + 1
                )
            )
        }
    }

    private fun nextPage(directions: NavDirections) {
        findNavController().navigate(directions)
    }

}