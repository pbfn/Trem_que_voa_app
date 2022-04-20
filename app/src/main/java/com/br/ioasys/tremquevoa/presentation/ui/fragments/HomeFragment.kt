package com.br.ioasys.tremquevoa.presentation.ui.fragments

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.GridLayoutManager
import com.br.ioasys.tremquevoa.R
import com.br.ioasys.tremquevoa.databinding.FragmentHomeBinding
import com.br.ioasys.tremquevoa.presentation.adapters.AdapterDisabilities
import com.br.ioasys.tremquevoa.presentation.adapters.AdapterEvents
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.text.SimpleDateFormat
import java.util.*

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding get() = _binding!!

    private lateinit var adapterEvents: AdapterEvents
    private lateinit var adapterDisabilities: AdapterDisabilities
    private lateinit var customAlertDialogView: View


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
        setRecyclerViewEvents()
        customAlertDialogView = LayoutInflater.from(requireContext())
            .inflate(R.layout.pop_up_home, null, false)
        showDialog()
    }

    private fun setRecyclerViewEvents() {
        val layout = GridLayoutManager(requireContext(), 2, GridLayoutManager.HORIZONTAL, false)
        adapterEvents = AdapterEvents()
        binding.recyclerViewEvents.apply {
            adapter = adapterEvents
            layoutManager = layout
        }
    }

    private fun showDialog() {
        val dialog =
            MaterialAlertDialogBuilder(
                requireContext(),
                R.style.MaterialAlertDialog_Rounded
            )
        dialog.setView(customAlertDialogView)
        val text =
            customAlertDialogView.findViewById(R.id.textViewMotivationalMessage) as AppCompatTextView
        val radioGroup = customAlertDialogView.findViewById(R.id.radioGroup) as RadioGroup
        dialog.setPositiveButton("Teste", object : DialogInterface.OnClickListener {
            override fun onClick(p0: DialogInterface?, p1: Int) {
                text.text = radioGroup.checkedRadioButtonId.toString()
            }

        })
        val date = Calendar.getInstance().time
        val dateTimeFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault())

        text.text = dateTimeFormat.format(date)
        dialog.create()
        dialog.show()
    }

}