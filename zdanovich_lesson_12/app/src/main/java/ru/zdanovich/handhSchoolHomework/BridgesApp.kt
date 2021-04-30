package ru.zdanovich.handhSchoolHomework

import android.app.Application
import android.content.Context

class BridgesApp: Application() {
    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

    companion object {
        private var context: Context? = null
        fun context(): Context = context ?: throw IllegalStateException()
    }
}