package com.br.ioasys.tremquevoa.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.br.ioasys.tremquevoa.databinding.EventsItemAdpterBinding
import com.br.ioasys.tremquevoa.domain.model.Event

class AdapterEvents() : RecyclerView.Adapter<AdapterEvents.AdpterEventsViewHolder>() {

        class AdpterEventsViewHolder(itemView: EventsItemAdpterBinding) :
            RecyclerView.ViewHolder(itemView.root) {
            val dateEvent = itemView.textViewDateEvent
            val titleEvent = itemView.textViewTitleEvent
            //val interestEvent = itemView.interestCardEvent TODO como pegar essa informação dos interesses?
            //val confirmed = itemView.confirmedCardEvent TODO como implementar essa parte de confirmação?
            val city = itemView.textViewLocalEvent
        }

        private val differCallback = object : DiffUtil.ItemCallback<Event>() {
            override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
                return oldItem == newItem
            }
        }

        val differ = AsyncListDiffer(this, differCallback)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdpterEventsViewHolder {
            val binding = EventsItemAdpterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return AdpterEventsViewHolder(binding)
        }

        override fun onBindViewHolder(holder: AdpterEventsViewHolder, position: Int) {
            val events = differ.currentList[position]
            holder.apply {
                dateEvent.text = events.date
                titleEvent.text = events.name
                city.text = events.city
            }
        }

        override fun getItemCount(): Int {
            return differ.currentList.size
        }
}