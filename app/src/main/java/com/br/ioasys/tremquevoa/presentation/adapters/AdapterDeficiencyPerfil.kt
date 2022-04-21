package com.br.ioasys.tremquevoa.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.br.ioasys.tremquevoa.databinding.DeficiencyPerfilItemAdapterBinding
import com.br.ioasys.tremquevoa.databinding.InterestsPerfilItemAdapterBinding
import com.br.ioasys.tremquevoa.domain.model.Disabilities
import com.br.ioasys.tremquevoa.domain.model.Interests

class AdapterDeficiencyPerfil() :
    RecyclerView.Adapter<AdapterDeficiencyPerfil.AdapterDeficiencyPerfilPerfilViewHolder>() {

    class AdapterDeficiencyPerfilPerfilViewHolder(itemView: DeficiencyPerfilItemAdapterBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        val title = itemView.textViewTitle
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
    ): AdapterDeficiencyPerfilPerfilViewHolder {
        val binding = DeficiencyPerfilItemAdapterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return AdapterDeficiencyPerfilPerfilViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AdapterDeficiencyPerfilPerfilViewHolder, position: Int) {
        val disabilities = differ.currentList[position]
        holder.apply {
            title.text = disabilities.name
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

}