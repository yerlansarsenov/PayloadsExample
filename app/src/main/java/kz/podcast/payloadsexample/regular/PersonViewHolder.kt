package kz.podcast.payloadsexample.regular

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.google.android.material.textview.MaterialTextView
import kz.podcast.payloadsexample.R
import kz.podcast.payloadsexample.databinding.ItemPersonBinding
import kz.podcast.payloadsexample.models.InternetStatus
import kz.podcast.payloadsexample.models.Person
import kz.podcast.payloadsexample.utils.setTextColorRes
import kz.podcast.payloadsexample.utils.showOrHide

class PersonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var binding: ItemPersonBinding? = null

    fun bind(person: Person, payloads: MutableList<Any> = mutableListOf()) {
        binding = ItemPersonBinding.bind(itemView)
        if (payloads.isEmpty()) {
            bind(person)
            return
        }
        val newInternetStatus = payloads[0] as? InternetStatus ?: return
        setPersonsInternetStatus(
            textInternetStatus = binding?.textInternetStatus ?: return,
            internetStatus = newInternetStatus
        )
    }

    private fun bind(person: Person) {
        with(binding ?: return) {
            imagePoster.load(person.avaUrl.value)
            textName.text = person.name
            setPersonsInternetStatus(textInternetStatus, person.internetStatus)
        }
    }

    private fun setPersonsInternetStatus(
        textInternetStatus: MaterialTextView,
        internetStatus: InternetStatus
    ) {
        textInternetStatus.showOrHide(internetStatus != InternetStatus.Undefined)
        val (statusTextRes, statusColorRes) = when (internetStatus) {
            InternetStatus.Online -> {
                R.string.online to R.color.green
            }
            InternetStatus.Offline -> {
                R.string.offline to R.color.red
            }
            else -> return
        }
        textInternetStatus.text = textInternetStatus.context.getString(statusTextRes)
        textInternetStatus.setTextColorRes(statusColorRes)
    }
}