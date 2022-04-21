package com.br.ioasys.tremquevoa.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.br.ioasys.tremquevoa.databinding.WellnessItemAdapterBinding
import com.br.ioasys.tremquevoa.domain.model.Wellness
import com.bumptech.glide.Glide

class AdapterWellness() : RecyclerView.Adapter<AdapterWellness.AdapterWellnessViewHolder>() {

    var itemLimit = 3

    class AdapterWellnessViewHolder(itemView: WellnessItemAdapterBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        val image = itemView.imageCard
        val title = itemView.textViewTitle
        val subtitle = itemView.textViewSubTitle
    }

    private val differCallback = object : DiffUtil.ItemCallback<Wellness>() {
        override fun areItemsTheSame(oldItem: Wellness, newItem: Wellness): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Wellness, newItem: Wellness): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterWellnessViewHolder {
        val binding = WellnessItemAdapterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return AdapterWellnessViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AdapterWellnessViewHolder, position: Int) {
        val wellness = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(wellness.imageUrl)
                .into(holder.image)
        }
        holder.apply {
            title.text = wellness.title
            subtitle.text = wellness.description
        }
    }

    override fun getItemCount(): Int {
        return if (differ.currentList.size > itemLimit) {
            itemLimit
        } else {
            differ.currentList.size
        }

    }
}