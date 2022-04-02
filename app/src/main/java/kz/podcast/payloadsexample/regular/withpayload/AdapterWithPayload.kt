package kz.podcast.payloadsexample.regular.withpayload

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import kz.podcast.payloadsexample.R
import kz.podcast.payloadsexample.models.Person
import kz.podcast.payloadsexample.regular.PersonViewHolder

class AdapterWithPayload : ListAdapter<Person, PersonViewHolder>(
    PersonDiffUtilCallback
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        return PersonViewHolder(
            itemView = LayoutInflater.from(
                parent.context
            ).inflate(
                R.layout.item_person,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    override fun onBindViewHolder(
        holder: PersonViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
            return
        }
        holder.bind(currentList[position], payloads)
    }
}

object PersonDiffUtilCallback : DiffUtil.ItemCallback<Person>() {

    override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean =
        oldItem == newItem

    override fun getChangePayload(oldItem: Person, newItem: Person): Any? {
        return when {
            oldItem.internetStatus != newItem.internetStatus -> newItem.internetStatus
            else -> null
        }
    }
}
