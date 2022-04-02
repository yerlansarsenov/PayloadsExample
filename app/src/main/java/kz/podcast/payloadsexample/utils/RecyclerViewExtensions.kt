package kz.podcast.payloadsexample.utils

import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kz.podcast.payloadsexample.R

fun RecyclerView.addVerticalLineDivider(
    @ColorRes lineColor: Int = R.color.dark_gray,
    verticalMarginInDp: Int = 0,
    startPaddingInDp: Int = 16,
    endPaddingInDp: Int = 16
) {
    val divider = ContextCompat.getDrawable(context, R.drawable.divider_line) ?: return
    divider.setTint(ContextCompat.getColor(this.context, lineColor))
    val dividerItemDecoration = DividerItemDecorator(
        divider,
        verticalMarginInDp = verticalMarginInDp,
        startPaddingInDp = startPaddingInDp,
        endPaddingInDp = endPaddingInDp
    )
    this.addItemDecoration(dividerItemDecoration)
}
