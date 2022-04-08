package com.br.ioasys.tremquevoa.presentation.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.br.ioasys.tremquevoa.R
import com.br.ioasys.tremquevoa.databinding.ButtonsOptionsItemAdapterBinding
import com.br.ioasys.tremquevoa.domain.model.Disabilities

class AdapterDisabilities() :
    RecyclerView.Adapter<AdapterDisabilities.AdapterDisabilitiesViewHolder>() {

    var context: Context? = null

    public val listDisabilitiesSelected = arrayListOf<Disabilities>()

    class AdapterDisabilitiesViewHolder(itemView: ButtonsOptionsItemAdapterBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        val buttonOptions = itemView.buttonTypeDisabilities
    }

    private val differCallback = object : DiffUtil.ItemCallback<Disabilities>() {
        override fun areItemsTheSame(oldItem: Disabilities, newItem: Disabilities): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Disabilities, newItem: Disabilities): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdapterDisabilitiesViewHolder {
       val binding = ButtonsOptionsItemAdapterBinding.inflate(
           LayoutInflater.from(parent.context), parent, false
       )
        context = parent.context
        return AdapterDisabilitiesViewHolder(binding)
    }

    @SuppressLint("ResourceType")
    override fun onBindViewHolder(holder: AdapterDisabilitiesViewHolder, position: Int) {
        val disabilitie = differ.currentList[position]
        holder.apply {
            buttonOptions.text = disabilitie.name
            buttonOptions.setOnClickListener {
                Log.d(this.javaClass.simpleName, "clique do adapter")

                disabilitie.apply {
                    if(buttonOptions.isSelected) {
                        buttonOptions.isSelected = false
                        val disab = disabilitie.copy(active = false)
                        listDisabilitiesSelected.remove(disab)
                        Log.d(this.javaClass.simpleName, "lista selecionada: ${listDisabilitiesSelected.size}")
                    }else {
                        buttonOptions.isSelected = true
                        val disab = disabilitie.copy(active = true)
                        listDisabilitiesSelected.add(disab)
                        Log.d(this.javaClass.simpleName, "lista selecionada: ${listDisabilitiesSelected.size}")
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}