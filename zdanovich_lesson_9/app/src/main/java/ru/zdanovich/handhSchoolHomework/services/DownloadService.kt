package ru.zdanovich.handhSchoolHomework.services

import android.app.Service
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.IBinder
import kotlinx.coroutines.*
import okhttp3.Request
import ru.zdanovich.handhSchoolHomework.R
import ru.zdanovich.handhSchoolHomework.data.network.DownloadServiceNetworkModule
import java.io.File
import java.net.HttpURLConnection

class DownloadService : Service() {
    private val internalNotificationManager = NotificationManager()

    private var coroutineScope: CoroutineScope = createCoroutineScope()
    private var payloadJob: Job? = null
    private val okHttpClient = DownloadServiceNetworkModule.httpClient

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onCreate() {
        super.onCreate()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notification = internalNotificationManager.createNotificationForOreo(
                this,
                getString(R.string.download_service_title)
            )
            startForeground(NotificationManager.NOTIFICATION_ID, notification)
        }
    }

    private fun startJob(url: String) {
        if (payloadJob?.isActive == true) {
            payloadJob?.cancel()
        }

        payloadJob = coroutineScope.launch {
            val name = "image.jpg"
            val outputDir = File(applicationContext.filesDir, "")
            val outputFile = File(outputDir, name)
            downloadFile(url, outputFile)
            stopForeground(false)
            stopSelf()
        }
    }

    private suspend fun downloadFile(url: String, file: File)  {
        val request = Request.Builder().url(url).build()
        val response = okHttpClient.newCall(request).execute()
        val body = response.body
        val responseCode = response.code

        if (responseCode >= HttpURLConnection.HTTP_OK &&
            responseCode < HttpURLConnection.HTTP_MULT_CHOICE &&
            body != null) {

            val length = body.contentLength()
            body.byteStream().apply {
                file.outputStream().use { fileOut ->
                    var bytesCopied = 0
                    val buffer = ByteArray(BUFFER_LENGTH_BYTES)
                    var bytes = read(buffer)
                    while (bytes >= 0) {
                        fileOut.write(buffer, 0, bytes)
                        bytesCopied += bytes
                        bytes = read(buffer)

                        val progress = ((bytesCopied * 100)/length).toInt()
                        internalNotificationManager.updateNotification(this@DownloadService, progress)
                    }

                    internalNotificationManager.createNotificationAfterJobDone(this@DownloadService, Uri.fromFile(file))
                }
            }

        } else {
            // Error
        }


    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.getStringExtra(URL_KEY)?.let {
            startJob(it)
        }

        return START_NOT_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineScope.cancel()
    }

    private fun createCoroutineScope() = CoroutineScope(Job() + Dispatchers.IO)

    companion object {
        const val URL_KEY = "url_key"
        const val BUFFER_LENGTH_BYTES = 1024
    }
}