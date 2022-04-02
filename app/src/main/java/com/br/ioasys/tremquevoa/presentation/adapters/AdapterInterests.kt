package com.br.ioasys.tremquevoa.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.br.ioasys.tremquevoa.databinding.InterestsItemAdapterBinding
import com.br.ioasys.tremquevoa.domain.model.Interests

class AdapterInterests() : RecyclerView.Adapter<AdapterInterests.AdapterInterestsViewHolder>() {


    class AdapterInterestsViewHolder(itemView: InterestsItemAdapterBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        val image = itemView.imageViewInterests
        val title = itemView.textViewInterest
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterInterestsViewHolder {
        val binding = InterestsItemAdapterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return AdapterInterestsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AdapterInterestsViewHolder, position: Int) {
        val interest = differ.currentList[position]
        holder.apply {
            title.text = interest.title
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}