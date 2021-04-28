package ru.zdanovich.handhSchoolHomework.views

import android.content.Context

fun Int.fromDpToPx(context: Context): Float =
    this * context.resources.displayMetrics.density