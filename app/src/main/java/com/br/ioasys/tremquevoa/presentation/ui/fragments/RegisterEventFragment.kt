package com.br.ioasys.tremquevoa.presentation.ui.fragments

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.br.ioasys.tremquevoa.databinding.FragmentRegisterEventBinding
import com.br.ioasys.tremquevoa.domain.model.Interests
import com.br.ioasys.tremquevoa.extensions.toInt
import com.br.ioasys.tremquevoa.presentation.adapters.AdapterActivities
import com.br.ioasys.tremquevoa.presentation.adapters.AdapterDisabilities
import com.br.ioasys.tremquevoa.presentation.viewmodel.RegisterEventViewModel
import com.br.ioasys.tremquevoa.util.ViewState
import org.koin.androidx.viewmodel.ext.android.getViewModel
import java.util.*

class RegisterEventFragment : Fragment() {

    private var _binding: FragmentRegisterEventBinding? = null
    private val binding: FragmentRegisterEventBinding get() = _binding!!


    private var categorySelected: Interests? = null

    private lateinit var adapterDisabilities: AdapterDisabilities

    private val registerEventViewModel: RegisterEventViewModel by lazy {
        getViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentRegisterEventBinding.inflate(inflater, container, false).apply {
        Log.d("EventFragment", "eu passei no onCreate")
        _binding = this
    }.root

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListener()
        addObserver()
        setRecycleViewButtonsOptions()
        registerEventViewModel.fetchActivities()
        registerEventViewModel.fetchDisabilities()
    }

    private fun setListener() {
        binding.apply {
            buttonRegisterEvent.setOnClickListener {
                registerEvent()
            }

            imgInputDate.setOnClickListener {
                datePickerDialog()
            }

            imgInputStartTime.setOnClickListener {
                timePickerDialogStart()
            }

            imgInputEndTime.setOnClickListener {
                timePickerDialogEnd()
            }
        }
    }

    private fun addObserver() {
        registerEventViewModel.event.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ViewState.Loading -> {

                }
                is ViewState.Success -> {

                    Toast.makeText(
                        requireContext(),
                        "Evento cadastrado com sucesso",
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

        registerEventViewModel.activities.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ViewState.Loading -> {

                }

                is ViewState.Success -> {
                    val adapter = AdapterActivities(
                        requireContext(),
                        response.data
                    )
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

                    binding.autoCompleteCategory.apply {
                        setOnItemClickListener { adapterView, view, i, l ->
                            categorySelected = response.data[i]

                            setText(categorySelected?.title)
                            Log.d("Category", "categoria selecionada ${categorySelected?.title}")
                        }

                    }

                    binding.autoCompleteCategory.setAdapter(adapter)

                }

                is ViewState.Error -> {
                    Toast.makeText(
                        requireContext(),
                        "Houve uma falha ao pegar as activities",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        registerEventViewModel.disabilities.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ViewState.Loading -> {

                }
                is ViewState.Success -> {
                    adapterDisabilities.differ.submitList(response.data)
                }
                is ViewState.Error -> {

                }
            }
        }

    }

    private fun datePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePikerDialog = DatePickerDialog(
            requireContext(),
            { view, mYear, mMonth, mDay ->
                binding.customDate.input.setText("$mDay/$mMonth/$mYear")
            },
            day,
            month,
            year
        )
        datePikerDialog.show()
    }

    private fun timePickerDialogStart() {

        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        TimePickerDialog(
            requireContext(),
            TimePickerDialog.OnTimeSetListener { view, mHour, mMinute ->
                binding.customStartTime.input.setText("$mHour:$mMinute")
            },
            hour,
            minute,
            false
        ).show()
    }

    private fun timePickerDialogEnd() {

        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        TimePickerDialog(
            requireContext(),
            TimePickerDialog.OnTimeSetListener { view, mHour, mMinute ->
                binding.customEndTime.input.setText("$mHour:$mMinute")
            },
            hour,
            minute,
            false
        ).show()
    }

    private fun handIsOnline(): Boolean {
        return binding.autoCompleteModality.isSelected
    }

    private fun registerEvent() {
        registerEventViewModel.registerEvent(
            name = binding.customNameEvent.input.text.toString(),
            description = binding.customDescription.input.text.toString(),
            isOnline = handIsOnline(),
            date = binding.customDate.input.text.toString(),
            minimumAge = binding.customMinAge.input.text.toInt() ?: 0,
            maxParticipants = binding.customMaxParticipants.input.text.toInt() ?: 0,
            startTime = timePickerDialogStart().toString(),
            endTime = timePickerDialogEnd().toString(),
            activityId = categorySelected?.id ?: "",
            userIdentity = "",
            isAccessible = true
        )
    }

    private fun setRecycleViewButtonsOptions() {
        val layout = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
        adapterDisabilities = AdapterDisabilities()
        binding.recyclerViewButtonOptions.apply {
            adapter = adapterDisabilities
            layoutManager = layout
        }
    }
}