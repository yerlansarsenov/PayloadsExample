package kz.podcast.payloadsexample.utils

import android.view.View
import androidx.annotation.ColorRes
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.textview.MaterialTextView

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.showOrHide(show: Boolean) {
    when (show) {
        true -> {
            show()
        }
        false -> {
            hide()
        }
    }
}

fun MaterialTextView.setTextColorRes(@ColorRes resId: Int) {
    setTextColor(
        ResourcesCompat.getColor(resources, resId, null)
    )
}
