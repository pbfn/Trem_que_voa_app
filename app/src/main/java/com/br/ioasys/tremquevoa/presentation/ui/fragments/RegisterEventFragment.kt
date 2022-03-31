package com.br.ioasys.tremquevoa.presentation.ui.fragments

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.br.ioasys.tremquevoa.databinding.FragmentRegisterEventBinding
import com.br.ioasys.tremquevoa.extensions.toInt
import com.br.ioasys.tremquevoa.presentation.viewmodel.RegisterEventViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel
import java.util.*

class RegisterEventFragment : Fragment() {

    private var _binding: FragmentRegisterEventBinding? = null
    private val binding: FragmentRegisterEventBinding get() = _binding!!

    private val registerEventViewModel: RegisterEventViewModel by lazy {
        getViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentRegisterEventBinding.inflate(inflater, container, false).apply {
        _binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListener()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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
        registerEventViewModel.event.observe(viewLifecycleOwner){}
        //observar os estados
    }

    private fun dataPickDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePikerDialog = DatePickerDialog(requireContext(), DatePickerDialog.OnDateSetListener { view, mYear, mMonth, mDay ->
            binding.textViewDatePikerEvent.setText(""+ mYear + "-"+ mMonth +"-"+ mDay)
        }, year, month, day)
        datePikerDialog.show()
    }

    private fun handAcessible(): Boolean {
       return binding.radioButtonAcessible.isChecked
    }

    private fun registerEvent() {
        registerEventViewModel.registerEvent(
            name = binding.editTextEventName.text.toString(),
            description = binding.editTextDescription.text.toString(),
            date = binding.textViewDatePikerEvent.text.toString(),
            minimumAge = binding.editTextMinAge.text.toInt(),
            startTime = binding.editTextStartTime.text.toString(),
            endTime = binding.editTextEndTime.text.toString(),
            maxParticipants = binding.editTextMaxParticipants.text.toInt(),
            isAccessible = handAcessible()
        )
    }
}