package kz.podcast.payloadsexample.regular.withoutpayload

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import kz.podcast.payloadsexample.R
import kz.podcast.payloadsexample.models.Person
import kz.podcast.payloadsexample.regular.PersonViewHolder

class AdapterWithoutPayload : ListAdapter<Person, PersonViewHolder>(
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
}

object PersonDiffUtilCallback : DiffUtil.ItemCallback<Person>() {

    override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean =
        oldItem == newItem
}
