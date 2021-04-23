package ru.zdanovich.handhSchoolHomework.broadcastReceivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class DownloadBroadcastReceiver(private val listener: DownloadBroadcastListener) :
    BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        when (intent?.action) {
            ACTION_DOWNLOAD_PROGRESS -> listener.updateProgressBar(
                intent.getIntExtra(PROGRESS_VALUE, 0)
            )
        }
    }

    companion object {
        const val PROGRESS_VALUE = "PROGRESS_VALUE"
        const val ACTION_DOWNLOAD_PROGRESS = "ACTION_DOWNLOAD_PROGRESS"
        //const val ACTION_DOWNLOAD_FINISH = "ACTION_DOWNLOAD_FINISH"
        //const val ACTION_DOWNLOAD_ERROR = "ACTION_DOWNLOAD_ERROR"
    }

    interface DownloadBroadcastListener {
        fun updateProgressBar(progress: Int)
    }
}