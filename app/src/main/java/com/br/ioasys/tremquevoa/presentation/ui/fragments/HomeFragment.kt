package com.br.ioasys.tremquevoa.presentation.ui.fragments

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.appcompat.widget.AppCompatTextView
import com.br.ioasys.tremquevoa.R
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.br.ioasys.tremquevoa.databinding.FragmentHomeBinding
import com.br.ioasys.tremquevoa.domain.model.Event
import com.br.ioasys.tremquevoa.domain.model.Message
import com.br.ioasys.tremquevoa.domain.model.User
import com.br.ioasys.tremquevoa.presentation.adapters.AdapterEvents
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.text.SimpleDateFormat
import java.util.*
import com.br.ioasys.tremquevoa.presentation.adapters.EventClickListener
import com.br.ioasys.tremquevoa.presentation.viewmodel.HomeViewModel
import com.br.ioasys.tremquevoa.util.ViewState
import org.koin.androidx.viewmodel.ext.android.getViewModel

class HomeFragment : Fragment(), EventClickListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding get() = _binding!!
    private var user: User? = null
    private val homeViewModel: HomeViewModel by lazy {
        getViewModel()
    }

    private lateinit var adapterEventsPromoted: AdapterEvents
    private lateinit var adapterEventsOnline: AdapterEvents
    private lateinit var adapterEventsRecommended: AdapterEvents
    private lateinit var customAlertDialogView: View
    private lateinit var dateNow: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentHomeBinding.inflate(layoutInflater, container, false).apply {
        _binding = this
    }.root

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addObserver()
        verifyDateLogin()
        setRecyclerViewEvents()
        homeViewModel.getEvent(user?.token ?: "")
        customAlertDialogView = LayoutInflater.from(requireContext())
            .inflate(R.layout.pop_up_home, null, false)

    }

    private fun addObserver() {
        homeViewModel.user.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ViewState.Loading -> {

                }
                is ViewState.Success -> {
                    user = response.data
                }

                is ViewState.Error -> {

                }
                else -> {

                }
            }
        }

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

        homeViewModel.date.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ViewState.Success -> {
                    val dateTimeFormat = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
                    if(response.data.isEmpty()){
                        homeViewModel.getDailyMessage(user?.token ?: "")
                    }else{
                        val lastDateLogin = dateTimeFormat.parse(response.data)
                        val dateLogin = dateTimeFormat.parse(dateNow)
                        if (dateLogin > lastDateLogin) {
                            homeViewModel.getDailyMessage(user?.token ?: "")
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
    }

    private fun showDialog(dailyMessage: Message) {
        val dialog =
            MaterialAlertDialogBuilder(
                requireContext(),
                R.style.MaterialAlertDialog_Rounded
            )
        dialog.setView(customAlertDialogView)
        val text =
            customAlertDialogView.findViewById(R.id.textViewMotivationalMessage) as AppCompatTextView
        val radioGroup = customAlertDialogView.findViewById(R.id.radioGroup) as RadioGroup

        dialog.setPositiveButton("Confirmar", object : DialogInterface.OnClickListener {
            override fun onClick(p0: DialogInterface?, p1: Int) {

            }
        })
        text.text = dailyMessage.text

        dialog.create()
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
}