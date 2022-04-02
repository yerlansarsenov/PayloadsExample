package kz.podcast.payloadsexample.utils

import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.roundToInt

class DividerItemDecorator(
    private val mDivider: Drawable,
    private val verticalMarginInDp: Int = 0,
    private val startPaddingInDp: Int = 0,
    private val endPaddingInDp: Int = 0
) : RecyclerView.ItemDecoration() {
    private val mBounds = Rect()
    override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        canvas.save()
        val left: Int
        val right: Int
        val startMargin = Resources.getSystem().displayMetrics.density * startPaddingInDp
        val endMargin = Resources.getSystem().displayMetrics.density * endPaddingInDp
        if (parent.clipToPadding) {
            left = parent.paddingLeft + startMargin.toInt()
            right = parent.width - parent.paddingRight - endMargin.toInt()
            canvas.clipRect(
                left,
                parent.paddingTop,
                right,
                parent.height - parent.paddingBottom
            )
        } else {
            left = 0 + startMargin.toInt()
            right = parent.width - endMargin.toInt()
        }
        val childCount = parent.childCount
        for (i in 0 until childCount - 1) {
            val child = parent.getChildAt(i)
            parent.getDecoratedBoundsWithMargins(child, mBounds)
            val bottom = (mBounds.bottom + child.translationY.roundToInt())
            val top = bottom - mDivider.intrinsicHeight
            mDivider.setBounds(left, top, right, bottom)
            mDivider.draw(canvas)
        }
        canvas.restore()
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val verticalMargin = Resources.getSystem().displayMetrics.density * verticalMarginInDp
        when (parent.getChildAdapterPosition(view)) {
            0 -> {
                outRect[0, 0, 0] = mDivider.intrinsicHeight
                outRect.bottom = verticalMargin.toInt()
            }
            state.itemCount - 1 -> {
                outRect[0, 0, 0] = mDivider.intrinsicHeight
                outRect.setEmpty()
                outRect.top = verticalMargin.toInt()
            }
            else -> {
                outRect[0, 0, 0] = mDivider.intrinsicHeight
                outRect.top = verticalMargin.toInt()
                outRect.bottom = verticalMargin.toInt()
            }
        }
    }
}
