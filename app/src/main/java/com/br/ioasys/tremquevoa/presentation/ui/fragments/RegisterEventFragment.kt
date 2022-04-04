package com.br.ioasys.tremquevoa.presentation.ui.fragments

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.br.ioasys.tremquevoa.databinding.FragmentRegisterEventBinding
import com.br.ioasys.tremquevoa.domain.model.Activities
import com.br.ioasys.tremquevoa.extensions.toInt
import com.br.ioasys.tremquevoa.presentation.AdapterActivities
import com.br.ioasys.tremquevoa.presentation.viewmodel.RegisterEventViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel
import java.util.*

class RegisterEventFragment : Fragment() {

    private var _binding: FragmentRegisterEventBinding? = null
    private val binding: FragmentRegisterEventBinding get() = _binding!!

    private var activitySelected: Activities? = null

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
        registerEventViewModel.fetchActivities()
    }

    private fun setListener() {
        binding.apply {
            buttonRegisterEvent.setOnClickListener {
                registerEvent()
            }

            buttonDatePikerEvent.setOnClickListener {
                dataPickDialog()
            }
        }
    }

    private fun addObserver() {
        registerEventViewModel.event.observe(viewLifecycleOwner) { response ->
            when (response) {
                is com.br.ioasys.tremquevoa.util.ViewState.Loading -> {

                }
                is com.br.ioasys.tremquevoa.util.ViewState.Success -> {
                    Toast.makeText(
                        requireContext(),
                        "Evento cadastrado com sucesso",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is com.br.ioasys.tremquevoa.util.ViewState.Error -> {
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
                is com.br.ioasys.tremquevoa.util.ViewState.Loading -> {

                }

                is com.br.ioasys.tremquevoa.util.ViewState.Success -> {
                    val adapter = AdapterActivities(
                        requireContext(),
                        response.data
                    )
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

                    binding.autoCompleteActivity.setOnItemClickListener { adapterView, view, i, l ->
                        activitySelected = response.data[i]
                    }
                    binding.autoCompleteActivity.setAdapter(adapter)
                }

                is com.br.ioasys.tremquevoa.util.ViewState.Error -> {
                    Toast.makeText(
                        requireContext(),
                        "Houve uma falha ao pegar as activities",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun dataPickDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePikerDialog = DatePickerDialog(
            requireContext(),
            { view, mYear, mMonth, mDay ->
                binding.textViewDatePikerEvent.setText("" + mYear + "-" + mMonth + "-" + mDay)
            },
            year,
            month,
            day
        )
        datePikerDialog.show()
    }

    private fun handAccessible(): Boolean {
        return binding.radioButtonIsAcessible.isChecked
    }

    private fun handIsOnline(): Boolean {
        return binding.radioButtonIsOnline.isChecked
    }

    private fun registerEvent() {
        registerEventViewModel.registerEvent(
            name = binding.editTextEventName.text.toString(),
            description = binding.editTextDescription.text.toString(),
            isOnline = handIsOnline(),
            date = binding.textViewDatePikerEvent.text.toString(),
            minimumAge = binding.editTextMinAge.text.toInt() ?: 0,
            maxParticipants = binding.editTextMaxParticipants.text.toInt() ?: 0,
            startTime = binding.editTextStartTime.text.toString(),
            endTime = binding.editTextEndTime.text.toString(),
            activityId = activitySelected?.id?:"",
            userIdentity = binding.editTextIdentity.text.toString(),
            isAccessible = handAccessible()
        )
    }
}