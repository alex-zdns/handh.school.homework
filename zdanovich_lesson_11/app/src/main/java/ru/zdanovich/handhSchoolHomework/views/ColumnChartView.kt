package ru.zdanovich.handhSchoolHomework.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
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

    private val columnBaseLineBottom = COLUMN_BASE_LINE_BOTTOM.fromDpToPx(context)
    private val columnBaseLineTop = COLUMN_BASE_LINE_TOP.fromDpToPx(context)

    private var columnSpacing = 0f

    private var columnData: List<Data> = emptyList()
    private var columnsCoordinates: List<RectF> = emptyList()

    fun setData(data: List<Data>) {
        columnData = data
        columnsCoordinates = emptyList()
        invalidate()
    }

    private fun calculateColumnsCoordinates() {
        val maxValue = columnData.maxOf { it.value }
        columnSpacing = (width * 1.0f - columnWidth * columnData.size) / (columnData.size + 1)
        columnsCoordinates = columnData.mapIndexed { i, item ->
            RectF(
                columnSpacing + i * (columnSpacing + columnWidth),
                columnBaseLineBottom - columnBaseLineTop * item.value / (maxValue),
                columnSpacing + columnWidth + i * (columnSpacing + columnWidth),
                columnBaseLineBottom
            )
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (columnsCoordinates.isEmpty()) calculateColumnsCoordinates()

        for (i in columnsCoordinates.indices) {
            canvas.drawRoundRect(columnsCoordinates[i], COLUMN_RADIUS, COLUMN_RADIUS, columnPaint)
            drawCaption(canvas, i)
            drawLabel(canvas, i)
        }
    }

    private fun drawCaption(canvas: Canvas, index: Int) {
        canvas.drawText(
            columnData[index].caption,
            columnsCoordinates[index].left + columnWidth / 2,
            columnBaseLineBottom + columnMarginBottom,
            captionPaint
        )
    }

    private fun drawLabel(canvas: Canvas, index: Int) {
        canvas.drawText(
            columnData[index].value.toString(),
            columnsCoordinates[index].left + columnWidth / 2,
            columnsCoordinates[index].top - columnMarginTop,
            columnPaint
        )
    }


    companion object {
        const val COLUMN_MARGIN_BOTTOM_DP = 16
        const val COLUMN_MARGIN_TOP_DP = 12
        const val COLUMN_RADIUS = 100f
        const val COLUMN_BASE_LINE_BOTTOM = 150
        const val COLUMN_BASE_LINE_TOP = 100
    }

    data class Data(val value: Int, val caption: String)
}