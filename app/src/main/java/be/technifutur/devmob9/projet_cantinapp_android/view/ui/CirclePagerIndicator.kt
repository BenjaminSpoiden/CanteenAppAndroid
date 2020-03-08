package be.technifutur.devmob9.projet_cantinapp_android.view.ui

import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Interpolator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CirclePagerIndicator: RecyclerView.ItemDecoration() {
    companion object{
        val DP: Float = Resources.getSystem().displayMetrics.density
        val colorInactive = Color.argb(255, 126, 126, 126) //Dark Grey
        val colorActive = Color.argb(255, 1, 94, 11) //Dark Green
    }

    private val indicatorHeight: Int = (DP * 8).toInt() //Height of space the indicator takes up at the bottom of the view
    private val indicatorStrokeWidth = DP * 2 //Indicator stroke width
    private val indicatorItemLength = DP * 16 //Indicator width
    private val indicatorItemPadding = DP * 4 //Padding between indicators


    //More natural effects
    private val interpolator: Interpolator = AccelerateDecelerateInterpolator()
    private val paint: Paint = Paint()

    init {
        paint.strokeCap = Paint.Cap.ROUND
        paint.strokeWidth = indicatorStrokeWidth
        paint.style = Paint.Style.STROKE
        paint.isAntiAlias = true
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
        val itemCount: Int? = parent.adapter?.itemCount


        val totalLength: Float
        val paddingBetweenItems: Float
        val indicatorTotalWidth: Float
        val indicatorStartX: Float
        val indicatorPosY: Float

        if (itemCount != null){
            totalLength = indicatorItemLength * itemCount
            paddingBetweenItems = 0.coerceAtLeast(itemCount - 1) * indicatorItemPadding
            indicatorTotalWidth = totalLength + paddingBetweenItems
            indicatorStartX = (parent.width - indicatorTotalWidth) / 2f //Permet de centrer les lignes au milieu de l'écran

            //Center Vertically
            indicatorPosY = parent.height - indicatorHeight * 2f
            drawInactiveIndicator(c, indicatorStartX, indicatorPosY, itemCount)

            //Find active page that should be Highlighted
            val layoutManager: LinearLayoutManager = parent.layoutManager as LinearLayoutManager
            val activePosition: Int = layoutManager.findFirstVisibleItemPosition()

            if (activePosition == RecyclerView.NO_POSITION){
                return
            }

            //Find offset Of active Page
            val activeChild: View? = layoutManager.findViewByPosition(activePosition)
            val left = activeChild?.left
            val right = activeChild?.right
            val width = activeChild?.width

            //On swipe the active item will be positioned from [-width, 0]
            //Interpolate offset for smooth animation
            val progress: Float
            if (left != null && right != null && width != null){
                progress = interpolator.getInterpolation((left * -1 /(width).toFloat()))
                drawHighlight(c, indicatorStartX, indicatorPosY, activePosition, progress, itemCount)
            }
        }
    }

    private fun drawInactiveIndicator(c: Canvas, indicatorStartX: Float, indicatorPosY: Float, itemCount: Int){
        paint.color =
            colorInactive
        val itemWidth = indicatorItemLength + indicatorItemPadding
        var start = indicatorStartX

        for (i in 1..itemCount){
            c.drawLine(start, indicatorPosY, start + indicatorItemLength, indicatorPosY, paint)
            start += itemWidth
        }
    }

    private fun drawHighlight(c: Canvas, indicatorStartX: Float, indicatorPosY: Float, highlightPosition: Int, progress: Float, itemCount: Int){
        paint.color =
            colorActive
        val itemWidth = indicatorItemLength + indicatorItemPadding

        if (progress == 0f) run {
            val highlightStart: Float = indicatorStartX + itemWidth * highlightPosition
            c.drawLine(highlightStart, indicatorPosY, highlightStart + indicatorItemLength, indicatorPosY, paint)
        } else{
            var highlightStart: Float = indicatorStartX + itemWidth * highlightPosition
            val partialLength: Float = indicatorItemLength * progress
            c.drawLine(highlightStart + partialLength, indicatorPosY, highlightStart + indicatorItemLength, indicatorPosY, paint)

            //Highlight overlapping
            if(highlightPosition < itemCount - 1){
                highlightStart += itemWidth
                c.drawLine(highlightStart, indicatorPosY, highlightStart + partialLength, indicatorPosY, paint);
            }
        }
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.bottom = indicatorHeight
    }
}

