package com.br.ioasys.tremquevoa.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.ThemedSpinnerAdapter
import com.br.ioasys.tremquevoa.domain.model.Activities

@SuppressLint("NewApi")
class AdapterActivities(
    context: Context,
    private val listActivities: List<Activities>
) : ArrayAdapter<Activities>(
    context,
    android.R.layout.simple_spinner_dropdown_item,
    listActivities
), ThemedSpinnerAdapter {

    override fun getItem(position: Int): Activities? {
        return listActivities[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return listActivities.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        super.getView(position, convertView, parent)
        val view = super.getView(position, convertView, parent)
        val tvNameActivities = view.findViewById<TextView>(android.R.id.text1)
        tvNameActivities.setText(listActivities[position].name)
        return view
    }
}