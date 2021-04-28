package ru.zdanovich.handhSchoolHomework.views

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import ru.zdanovich.handhSchoolHomework.R

class ColumnChartView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val attributes = context.obtainStyledAttributes(attrs, R.styleable.ColumnChartView)
    private val columnPaint = Paint().apply {
        color = attributes.getColor(R.styleable.ColumnChartView_columnColor, Color.RED)
        textAlign = Paint.Align.CENTER
        textSize = attributes.getDimension(
            R.styleable.ColumnChartView_columnLabelTextSize,
            24.fromDpToPx(context)
        )
    }

    private val captionPaint = Paint().apply {
        color = attributes.getColor(R.styleable.ColumnChartView_captionTextColor, Color.GREEN)
        textAlign = Paint.Align.CENTER
        textSize = attributes.getDimension(
            R.styleable.ColumnChartView_captionTextSize,
            30.fromDpToPx(context)
        )
    }

    private val columnWidth =
        attributes.getDimension(R.styleable.ColumnChartView_columnWidth, 16.fromDpToPx(context))
    private val columnMarginTop = COLUMN_MARGIN_TOP_DP.fromDpToPx(context)
    private val columnMarginBottom = COLUMN_MARGIN_BOTTOM_DP.fromDpToPx(context)

    private val columnBaseLineBottom = COLUMN_BASE_LINE_BOTTOM_DP.fromDpToPx(context)
    private val columnBaseLineTop = COLUMN_BASE_LINE_TOP_DP.fromDpToPx(context)

    var data: List<Data> = emptyList()
        set(value) {
            field = value
            columnsCoordinates = emptyList()
            invalidate()
        }

    private var columnsCoordinates: List<RectF> = emptyList()
    private var columnsTopCoordinates: List<Float> = emptyList()
    private var animator: ValueAnimator? = null
    var animationDuration = ANIMATION_DURATION

    private fun calculateColumnsCoordinates() {
        val maxValue = data.maxOf { it.value }
        val columnSpacing = (width * 1.0f - columnWidth * data.size) / (data.size + 1)

        columnsTopCoordinates = data.map {item ->
            columnBaseLineBottom - columnBaseLineTop * item.value / (maxValue)
        }

        columnsCoordinates = data.mapIndexed { i, item ->
            RectF(
                columnSpacing + i * (columnSpacing + columnWidth),
                columnsTopCoordinates[i],
                columnSpacing + columnWidth + i * (columnSpacing + columnWidth),
                columnBaseLineBottom
            )
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (columnsCoordinates.isEmpty()) calculateColumnsCoordinates()

        for (i in columnsCoordinates.indices) {
            drawColumn(canvas, columnsCoordinates[i])
            drawCaption(canvas, i)
            drawLabel(canvas, i)
        }
    }

    private fun drawCaption(canvas: Canvas, index: Int) {
        canvas.drawText(
            data[index].caption,
            columnsCoordinates[index].left + columnWidth / 2,
            columnBaseLineBottom + columnMarginBottom,
            captionPaint
        )
    }

    private fun drawLabel(canvas: Canvas, index: Int) {
        canvas.drawText(
            data[index].value.toString(),
            columnsCoordinates[index].left + columnWidth / 2,
            columnsCoordinates[index].top - columnMarginTop,
            columnPaint
        )
    }

    private fun drawColumn(canvas: Canvas, column: RectF) {
        canvas.drawRoundRect(column, COLUMN_RADIUS, COLUMN_RADIUS, columnPaint)
    }

    fun startMyAnimation() {
        animator?.cancel()

        columnsCoordinates.forEach { item ->
            item.top = item.bottom
        }

        animator =
            ValueAnimator.ofFloat(columnBaseLineBottom, (columnBaseLineBottom - columnBaseLineTop))
                .apply {
                    duration = animationDuration
                    interpolator = LinearInterpolator()
                    addUpdateListener { valueAnimator ->

                        for (i in columnsCoordinates.indices) {
                            val value = valueAnimator.animatedValue as Float
                            columnsCoordinates[i].top =
                                value.coerceAtLeast(columnsTopCoordinates[i])
                        }

                        invalidate()
                    }
                }

        animator?.start()
    }

    companion object {
        const val COLUMN_MARGIN_BOTTOM_DP = 16
        const val COLUMN_MARGIN_TOP_DP = 12
        const val COLUMN_RADIUS = 100f
        const val COLUMN_BASE_LINE_BOTTOM_DP = 150
        const val COLUMN_BASE_LINE_TOP_DP = 100
        const val ANIMATION_DURATION = 2000L
    }

    data class Data(val value: Int, val caption: String)
}