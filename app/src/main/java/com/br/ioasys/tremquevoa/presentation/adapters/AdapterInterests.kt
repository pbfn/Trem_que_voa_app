package com.br.ioasys.tremquevoa.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.br.ioasys.tremquevoa.R
import com.br.ioasys.tremquevoa.databinding.InterestsItemAdapterBinding
import com.br.ioasys.tremquevoa.domain.model.Interests
import com.bumptech.glide.Glide

class AdapterInterests() : RecyclerView.Adapter<AdapterInterests.AdapterInterestsViewHolder>() {


    var context: Context? = null

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
        context = parent.context
        return AdapterInterestsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AdapterInterestsViewHolder, position: Int) {
        val interest = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(interest.urlInactive)
                .into(holder.image)
        }
        holder.apply {
            title.text = interest.title
            image.setOnClickListener {
                interest.selected = !interest.selected
                if (interest.selected) {
                    holder.itemView.apply {
                        Glide.with(this).load(interest.urlActive)
                            .into(holder.image)
                    }
                } else {
                    holder.itemView.apply {
                        Glide.with(this).load(interest.urlInactive)
                            .into(holder.image)
                    }
                }
            }

        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}