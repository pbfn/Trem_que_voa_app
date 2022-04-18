package com.br.ioasys.tremquevoa.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.br.ioasys.tremquevoa.R
import com.br.ioasys.tremquevoa.databinding.EventsItemAdpterBinding
import com.br.ioasys.tremquevoa.domain.model.Event

class AdapterEvents(
    private val onEventClickListener: EventClickListener
) :ListAdapter<Event, AdapterEvents.EventListViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EventListViewHolder {
        return EventListViewHolder.create(parent, onEventClickListener)
    }

    override fun onBindViewHolder(holder: EventListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val DIFF_CALLBACK = object: DiffUtil.ItemCallback<Event>() {
            override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
                return oldItem == newItem
            }
        }
    }

    class EventListViewHolder(
        private val binding: EventsItemAdpterBinding,
        private val onEventClickListener: EventClickListener
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(event: Event) {
            binding.apply {
                textViewDateEvent.text = event.date
                textViewTitleEvent.text = event.name
                textViewLocalEvent.text = event.address?.city
                //val interestEvent = itemView.interestCardEvent TODO como pegar essa informação dos interesses?
                //val confirmed = itemView.confirmedCardEvent TODO como implementar essa parte de confirmação?

                imageCardEvent.load(event.url) {
                    error(R.drawable.img_event)
                }

                root.setOnClickListener {
                    onEventClickListener.onEventClickListener(event)
                }
            }
        }

        companion object {
            fun create(
                parent:ViewGroup,
                onEventClickListener: EventClickListener
            ):EventListViewHolder {
                val binding = EventsItemAdpterBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return EventListViewHolder(binding, onEventClickListener)
            }
        }
    }
}


