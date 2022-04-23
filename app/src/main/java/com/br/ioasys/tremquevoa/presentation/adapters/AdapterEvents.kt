package com.br.ioasys.tremquevoa.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.br.ioasys.tremquevoa.databinding.EventsItemAdpterBinding
import com.br.ioasys.tremquevoa.domain.model.Event
import com.br.ioasys.tremquevoa.extensions.*

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
        private val context: Context,
        private val binding: EventsItemAdpterBinding,
        private val onEventClickListener: EventClickListener
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(event: Event) {
            binding.apply {
                textViewDateEvent.text = event.date.toString(FORMAT_DATE_VIEW_SHORT)
                textViewTitleEvent.text = event.name
                textViewLocalEvent.text = event.address?.city
                textViewTitleCardCategory.text = event.activity?.title
                textViewTitleCardAccessibilities.show(
                    event.accessibilities?.isNotEmpty()?: false
                )
                iconSaveFavorite.setImageDrawable(event.getIconFavorite(context))
                imageCardEvent.setImageDrawable(event.interestImageDrawable(context))
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
                return EventListViewHolder(parent.context, binding, onEventClickListener)
            }
        }
    }
}


