package com.br.ioasys.tremquevoa.presentation.ui.fragments

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.br.ioasys.tremquevoa.R
import com.br.ioasys.tremquevoa.databinding.FragmentRegisterEventBinding
import com.br.ioasys.tremquevoa.domain.model.Interests
import com.br.ioasys.tremquevoa.extensions.*
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
    private var isOnline = false
    private var isYes = true
    private lateinit var adapterDisabilities: AdapterDisabilities
    private val registerEventViewModel: RegisterEventViewModel by lazy {
        getViewModel()
    }

    private lateinit var datePikerDialog: DatePickerDialog
    private lateinit var timePickerDialogStart: TimePickerDialog
    private lateinit var timePickerDialogEnd: TimePickerDialog
    private var date: GregorianCalendar = GregorianCalendar()
    private var startHour: GregorianCalendar? = null
    private var endHour: GregorianCalendar? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentRegisterEventBinding.inflate(inflater, container, false).apply {
        _binding = this
    }.root

    override fun onDestroyView() {
        super.onDestroyView()
        registerEventViewModel.resetViewState()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addObserver()
        setupDatePickerDialog()
        setupTimePickerDialogStart()
        setupTimePickerDialogEnd()
        setListener()
        setRecycleViewButtonsOptions()
        settingModality()
        settingPetFriendly()
        configToggleEventFree()
    }

    private fun setListener() {
        binding.apply {
            buttonRegisterEvent.setOnClickListener {
                registerEvent()
            }
            textViewAddGoogleMaps.setOnClickListener {
                val toast = Toast(requireContext())
                toast.showComingSoon(requireContext())
            }

            inputTextDate.ChangeIcon(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.date_range_fill_input
                )!!, ::showDatePicker
            )

            inputStartTime.ChangeIcon(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.time_five_input
                )!!, ::showTimePickerDialogStart
            )

            inputEndTime.ChangeIcon(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.time_five_input
                )!!, ::showTimePickerDialogEnd
            )

            /*customStreet.ChangeIcon(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.address_input
                )!!,
                {}
            )*/
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
                    binding.inputTextName.text.toString()
                    binding.inputDescription.text.toString()
                    isOnline = false
                    binding.inputUrl.text.toString()
                    date = GregorianCalendar()
                    startHour?.toString(FORMAT_HOUR) ?: ""
                    endHour?.toString(FORMAT_HOUR) ?: ""
                    isYes = false
                    binding.inputMaxParticipants.text?.clear()
                    categorySelected?.id ?: ""
                    binding.inputPrice.text?.clear()
                    binding.inputTextUserIdentity.text?.clear()
                    adapterDisabilities.listDisabilitiesSelected.map { it.id }
                    binding.inputStreet.text?.clear()
                    binding.inputNumber.text?.clear()
                    binding.inputCity.text?.clear()
                    binding.inputState.text?.clear()
                    binding.inputZipCode.text?.clear()
                    binding.inputReferences.text?.clear()
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
        registerEventViewModel.showProgressBar.observe(viewLifecycleOwner) { showProgressBar ->
            binding.progressBar.show(showProgressBar)
        }
    }

    private fun setupDatePickerDialog() {
        resources.configuration.setLocale(Locale("pt", "BR"))
        val today = Calendar.getInstance()
        val year = today.get(Calendar.YEAR)
        val month = today.get(Calendar.MONTH) + 1
        val day = today.get(Calendar.DAY_OF_MONTH)
        datePikerDialog = DatePickerDialog(
            requireContext(),
            { _, mYear, mMonth, mDay ->
                binding.inputTextDate.setText("$mDay-$mMonth-$mYear")
                date.set(GregorianCalendar.DAY_OF_MONTH, mDay)
                date.set(GregorianCalendar.MONTH, mMonth)
                date.set(GregorianCalendar.YEAR, mYear)
                Log.d("Date", "data selecionada $mDay-$mMonth-$mYear")
            },
            year,
            month,
            day
        )
    }

    private fun showDatePicker() {
        datePikerDialog.show()
    }

    private fun setupTimePickerDialogStart() {
        resources.configuration.setLocale(Locale("pt", "BR"))
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)
        timePickerDialogStart = TimePickerDialog(
            requireContext(),
            TimePickerDialog.OnTimeSetListener { view, mHour, mMinute ->
                binding.inputStartTime.setText("$mHour:$mMinute")
                startHour = GregorianCalendar().apply {
                    set(GregorianCalendar.HOUR, mHour)
                    set(GregorianCalendar.MINUTE, mMinute)
                    date.set(GregorianCalendar.HOUR, mHour)
                    date.set(GregorianCalendar.MINUTE, mMinute)
                }
                Log.d("Date", "startTime selecionada $mHour:$mMinute")
            },
            hour,
            minute,
            true
        )
    }

    private fun showTimePickerDialogStart() {
        timePickerDialogStart.show()
    }

    private fun setupTimePickerDialogEnd() {
        resources.configuration.setLocale(Locale("pt", "BR"))
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)
        timePickerDialogEnd = TimePickerDialog(
            requireContext(),
            TimePickerDialog.OnTimeSetListener { view, mHour, mMinute ->
                binding.inputEndTime.setText("$mHour:$mMinute")
                endHour = GregorianCalendar().apply {
                    set(GregorianCalendar.HOUR_OF_DAY, mHour)
                    set(GregorianCalendar.MINUTE, mMinute)
                }
                Log.d("Date", "startEnd selecionada $mHour:$mMinute")
            },
            hour,
            minute,
            true
        )
    }

    private fun showTimePickerDialogEnd() {
        timePickerDialogEnd.show()
    }

    private fun settingModality() {
        val modalities = resources.getStringArray(R.array.modalities_array)
        val adapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, modalities)
        binding.autoCompleteModality.apply {
            setAdapter(adapter)
            setOnItemClickListener { adapterView, view, i, l ->
                val itemSelected = modalities[i]
                val isOnline = getString(R.string.online).lowercase() == itemSelected.lowercase()
                this@RegisterEventFragment.isOnline = isOnline
                configViewIsOnline(isOnline)
            }
        }
    }

    private fun configToggleEventFree() {
        binding.apply {
            toggleFreeEvent.setOnCheckedChangeListener { compoundButton, b ->
                if (compoundButton.isChecked) {
                    compoundButton.isChecked = true
                    customPrice.invisible()
                } else {
                    compoundButton.isChecked = false
                    customPrice.visible()
                }
            }
        }

    }

    private fun configViewIsOnline(online: Boolean) {
        binding.apply {
            if (online) {
                customStreet.invisible()
                customNumber.invisible()
                customCity.invisible()
                customZipCode.invisible()
                customState.invisible()
                customReferences.invisible()
                textInputPetFriendly.invisible()
                textViewAddGoogleMaps.invisible()
                customUrl.visible()
            } else {
                customStreet.visible()
                customNumber.visible()
                customCity.visible()
                customZipCode.visible()
                customState.visible()
                customReferences.visible()
                textInputPetFriendly.visible()
                customUrl.invisible()
                Log.d("IsOnline", "configViewIsOnline()")
            }
        }
    }

    private fun settingPetFriendly() {
        val petFriendly = resources.getStringArray(R.array.pet_friendly_array)
        val adapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, petFriendly)
        binding.autoCompletePetFriendly.apply {
            setAdapter(adapter)
            setOnItemClickListener { adapterView, view, i, l ->
                val itemSelected = petFriendly[i]
                val isYes = getString(R.string.yes) == itemSelected
                this@RegisterEventFragment.isYes = isYes
                Log.d("IsPet", "settingPetFriendly()")
            }
        }
    }

    private fun registerEvent() {
        registerEventViewModel.registerEvent(
            name = binding.inputTextName.text.toString(),
            description = binding.inputDescription.text.toString(),
            isOnline = isOnline,
            url = binding.inputUrl.text.toString(),
            date = date?.toString(FORMAT_DATE) ?: "",
            startTime = startHour?.toString(FORMAT_HOUR) ?: "",
            endTime = endHour?.toString(FORMAT_HOUR) ?: "",
            isPetFriendly = isYes,
            maxParticipants = binding.inputMaxParticipants.text.toInt() ?: 0,
            activityId = categorySelected?.id ?: "",
            price = binding.inputPrice.text.toInt() ?: 0,
            userIdentity = binding.inputTextUserIdentity.text.toString(),
            accessibilities = adapterDisabilities.listDisabilitiesSelected.map { it.id },
            street = binding.inputStreet.text.toString(),
            number = binding.inputNumber.text.toInt() ?: 0,
            city = binding.inputCity.text.toString(),
            state = binding.inputState.text.toString(),
            zipCode = binding.inputZipCode.text.toString(),
            referencePoint = binding.inputReferences.text.toString(),
        )
    }

    private fun setRecycleViewButtonsOptions() {
        adapterDisabilities = AdapterDisabilities()
        binding.recyclerViewButtonOptions.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = adapterDisabilities
        }
    }
}