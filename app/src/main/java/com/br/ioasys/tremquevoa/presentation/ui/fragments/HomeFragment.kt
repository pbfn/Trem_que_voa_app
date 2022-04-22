package com.br.ioasys.tremquevoa.presentation.ui.fragments

import androidx.appcompat.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.appcompat.widget.AppCompatTextView
import com.br.ioasys.tremquevoa.R
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageButton
import androidx.core.content.ContextCompat
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.br.ioasys.tremquevoa.databinding.FragmentHomeBinding
import com.br.ioasys.tremquevoa.domain.model.*
import com.br.ioasys.tremquevoa.presentation.adapters.AdapterEvents
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.text.SimpleDateFormat
import java.util.*
import com.br.ioasys.tremquevoa.presentation.adapters.EventClickListener
import com.br.ioasys.tremquevoa.extensions.ChangeIcon
import com.br.ioasys.tremquevoa.extensions.show
import com.br.ioasys.tremquevoa.presentation.adapters.*
import com.br.ioasys.tremquevoa.presentation.viewmodel.HomeViewModel
import com.br.ioasys.tremquevoa.util.ViewState
import org.koin.androidx.viewmodel.ext.android.getViewModel

class HomeFragment : Fragment(), EventClickListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding get() = _binding!!
    private val homeViewModel: HomeViewModel by lazy {
        getViewModel()
    }

    private lateinit var adapterEventsPromoted: AdapterEvents
    private lateinit var adapterEventsOnline: AdapterEvents
    private lateinit var adapterEventsRecommended: AdapterEvents
    private lateinit var adapterInterests: AdapterInterestsPerfil
    private lateinit var adapterDisabilities: AdapterDisabilities
    private lateinit var adapterWellness: AdapterWellness
    private lateinit var customAlertDialogView: View
    private lateinit var dateNow: String
    private lateinit var dialog: AlertDialog

    private var listInterests: List<Interests> = listOf()
    private var listDisabilities: List<Disabilities> = listOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentHomeBinding.inflate(layoutInflater, container, false).apply {
        _binding = this
    }.root

    override fun onDestroyView() {
        super.onDestroyView()
        homeViewModel.resetViewState()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addObserver()
        verifyDateLogin()
        setRecyclerViewEvents()
        customAlertDialogView = LayoutInflater.from(requireContext())
            .inflate(R.layout.pop_up_home, null, false)
        setRecyclerViewDisabilities()
        setRecyclerViewInterest()
        setListener()
        createDialog()
    }

    private fun addObserver() {
        homeViewModel.events.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ViewState.Loading -> {
                }

                is ViewState.Success -> {
                    adapterEventsPromoted.submitList(response.data.listPromoted)
                    adapterEventsOnline.submitList(response.data.listOnline)
                    adapterEventsRecommended.submitList(response.data.listRecommended)
                }

                is ViewState.Error -> {
                    Toast.makeText(
                        requireContext(),
                        "Falha na lista de eventos",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        homeViewModel.interest.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ViewState.Loading -> {
                }

                is ViewState.Success -> {
                    listInterests = response.data
                    adapterInterests.differ.submitList(listInterests)
                }

                is ViewState.Error -> {
                    Toast.makeText(
                        requireContext(),
                        "Falha na lista de eventos",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        homeViewModel.disabilities.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ViewState.Loading -> {
                }

                is ViewState.Success -> {
                    listDisabilities = response.data
                    adapterDisabilities.differ.submitList(listDisabilities)
                }

                is ViewState.Error -> {
                    Toast.makeText(
                        requireContext(),
                        "Falha na lista de eventos",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        homeViewModel.date.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ViewState.Success -> {
                    val dateTimeFormat = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
                    if (response.data.isEmpty()) {
                        homeViewModel.getDailyMessage()
                    } else {
                        val lastDateLogin = dateTimeFormat.parse(response.data)
                        val dateLogin = dateTimeFormat.parse(dateNow)
                        if (dateLogin > lastDateLogin) {
                            homeViewModel.getDailyMessage()
                        }
                    }
                }
            }
        }

        homeViewModel.dailyMessage.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ViewState.Success -> {
                    showDialog(response.data)
                }
            }
        }

        homeViewModel.user.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ViewState.Success -> {
                    configureNameUserTitle(response.data.name)
                }
            }
        }

        homeViewModel.wellness.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ViewState.Success -> {
                    adapterWellness.differ.submitList(response.data)
                }
            }
        }
        homeViewModel.showProgressBar.observe(viewLifecycleOwner) { showProgressBar ->
            binding.progressBar.show(showProgressBar)
        }
    }

    private fun configureNameUserTitle(name: String) {
        binding.nameUser.text = String.format(getString(R.string.welcome), name)
    }

    private fun setListener() {
        binding.apply {
            customSearch.ChangeIcon(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_search,
                )!!, {}
            )
            customSearch.clearFocus()
            textViewViewMore.setOnClickListener {
                nextPage(HomeFragmentDirections.actionHomeFragmentToWellnessFragment())
            }
        }
    }

    private fun setRecyclerViewEvents() {
        adapterEventsPromoted = AdapterEvents(this)
        binding.recyclerViewPromotedEvents.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = adapterEventsPromoted
        }

        adapterEventsOnline = AdapterEvents(this)
        binding.recyclerViewOnline.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = adapterEventsOnline
        }

        adapterEventsRecommended = AdapterEvents(this)
        binding.recyclerViewRecomended.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = adapterEventsRecommended
        }

        adapterWellness = AdapterWellness()
        binding.recyclerViewWellness.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = adapterWellness

        }
    }

    private fun setRecyclerViewInterest() {
        adapterInterests = AdapterInterestsPerfil()
        binding.recyclerViewCategoryHome.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = adapterInterests
        }
    }

    private fun setRecyclerViewDisabilities() {
        adapterDisabilities = AdapterDisabilities()
        binding.recyclerViewAccessibitiesHome.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = adapterDisabilities
        }
    }

    private fun createDialog() {
        dialog =
            MaterialAlertDialogBuilder(
                requireContext(),
                R.style.MaterialAlertDialog_Rounded
            ).create()

        dialog.setView(customAlertDialogView)
        val radioGroup = customAlertDialogView.findViewById(R.id.radioGroup) as RadioGroup
        val btnConfirm = customAlertDialogView.findViewById(R.id.btnConfirm) as AppCompatTextView
        radioGroup.setOnCheckedChangeListener { _, _ ->
            btnConfirm.visibility = View.VISIBLE
        }
        val icClose = customAlertDialogView.findViewById(R.id.btnClose) as AppCompatImageButton
        icClose.setOnClickListener {
            dialog.dismiss()
        }
        btnConfirm.setOnClickListener {
            dialog.dismiss()
        }

    }

    private fun showDialog(dailyMessage: Message) {
        val text =
            customAlertDialogView.findViewById(R.id.textViewMotivationalMessage) as AppCompatTextView
        text.text = dailyMessage.text
        dialog.show()
    }

    private fun verifyDateLogin() {
        val date = Calendar.getInstance().time
        val dateTimeFormat = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
        dateNow = dateTimeFormat.format(date)
        homeViewModel.verifyDate(dateNow)
    }

    override fun onEventClickListener(event: Event) {
        EventFragment.newInstance(event).show(childFragmentManager, "event")
    }

    private fun nextPage(directions: NavDirections) {
        findNavController().navigate(directions)
    }
}