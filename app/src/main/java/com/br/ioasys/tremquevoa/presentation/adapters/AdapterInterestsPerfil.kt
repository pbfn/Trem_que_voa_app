package com.br.ioasys.tremquevoa.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.br.ioasys.tremquevoa.databinding.InterestsPerfilItemAdapterBinding
import com.br.ioasys.tremquevoa.domain.model.Interests

class AdapterInterestsPerfil() :
    RecyclerView.Adapter<AdapterInterestsPerfil.AdapterInterestsPerfilViewHolder>() {

    class AdapterInterestsPerfilViewHolder(itemView: InterestsPerfilItemAdapterBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        val title = itemView.textViewTitle
    }

    private val differCallback = object : DiffUtil.ItemCallback<Interests>() {
        override fun areItemsTheSame(oldItem: Interests, newItem: Interests): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Interests, newItem: Interests): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdapterInterestsPerfilViewHolder {
        val binding = InterestsPerfilItemAdapterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return AdapterInterestsPerfilViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AdapterInterestsPerfilViewHolder, position: Int) {
        val interests = differ.currentList[position]
        holder.apply {
            title.text = interests.title
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

}