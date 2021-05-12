package ru.zdanovich.handhSchoolHomework.broadcastReceivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.Uri
import ru.zdanovich.handhSchoolHomework.services.DownloadService

class DownloadReceiver(var listener: DownloadBroadcastListener?) : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
       when (intent.action) {
            DownloadService.ACTION_DOWNLOAD_PROGRESS -> {
                val progress: Int = intent.getIntExtra(DownloadService.PROGRESS_VALUE, 0)
                listener?.updateProgressBar(progress)
            }
           DownloadService.ACTION_DOWNLOAD_ERROR -> listener?.downloadError()
           DownloadService.ACTION_DOWNLOAD_FINISH -> {
               val uri = intent.getParcelableExtra<Uri>(DownloadService.URI_FINISH_KEY)

               uri?.let {
                   listener?.downloadFinish(it)
               }
           }
        }
    }

    interface DownloadBroadcastListener {
        fun updateProgressBar(progress: Int)
        fun downloadFinish(imageUri: Uri)
        fun downloadError()
    }
}