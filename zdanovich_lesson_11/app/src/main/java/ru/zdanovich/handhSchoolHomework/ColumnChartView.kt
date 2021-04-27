package ru.zdanovich.handhSchoolHomework

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class ColumnChartView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val attributes = context.obtainStyledAttributes(attrs, R.styleable.ColumnChartView)
    private val columnPaint = Paint().apply {
        color = attributes.getColor(R.styleable.ColumnChartView_columnColor, Color.RED)
        textAlign = Paint.Align.CENTER
        textSize = attributes.getDimension(R.styleable.ColumnChartView_columnLabelTextSize, 24f)
    }

    private val captionPaint = Paint().apply {
        color = attributes.getColor(R.styleable.ColumnChartView_captionTextColor, Color.GREEN)
        textAlign = Paint.Align.CENTER
        textSize = attributes.getDimension(R.styleable.ColumnChartView_captionTextSize, 30f)
    }

    private val columnWidth = attributes.getDimension(R.styleable.ColumnChartView_columnWidth, 16f)

    var dataList: List<Data> = emptyList()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val count = 6
        val columnSpacing = (width * 1.0f - columnWidth * count) / (count + 1)



        for (i in 0 until count) {
            val rectf = RectF(
                columnSpacing + i * (columnSpacing + columnWidth),
                (i + 1) * 60f,
                columnSpacing + columnWidth + i * (columnSpacing + columnWidth),
                400f
            )
            canvas.drawRoundRect(rectf, 100f, 100f, columnPaint)

            canvas.drawText(
                "24.05",
                columnSpacing + i * (columnSpacing + columnWidth) + columnWidth / 2,
                430f,
                captionPaint
            )

            canvas.drawText(
                ((i + 1) * 60).toString(),
                columnSpacing + i * (columnSpacing + columnWidth) + columnWidth / 2,
                (i + 1) * 60f - 30f,
                columnPaint
            )

        }


    }

    data class Data(val value: Int, val date: String)
}