package ru.zdanovich.handhSchoolHomework.services

import android.app.Service
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.*
import okhttp3.Request
import ru.zdanovich.handhSchoolHomework.BuildConfig
import ru.zdanovich.handhSchoolHomework.R
import ru.zdanovich.handhSchoolHomework.data.network.DownloadServiceNetworkModule
import java.io.File
import java.net.HttpURLConnection
import java.util.zip.ZipFile

class DownloadService : Service() {
    private val internalNotificationManager = NotificationManager()

    private var coroutineScope: CoroutineScope = createCoroutineScope()
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, exception ->
        coroutineScope.cancel()
        Log.e(
            this::class.simpleName,
            "CoroutineExceptionHandler got $exception in $coroutineContext"
        )

        val intent =
            Intent(ACTION_DOWNLOAD_ERROR)
        sendBroadcast(intent)
    }

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

        payloadJob = coroutineScope.launch(exceptionHandler) {
            val nameArchive = "image.zip"
            val outputDir = File(applicationContext.filesDir, "")
            val outputZipFile = File(outputDir, nameArchive)

            downloadFile(url, outputZipFile)

            val nameImage = "image.jpg"
            val outputImageFile = File(outputDir, nameImage)

            unZip(outputZipFile, outputImageFile)

            val intent = Intent(ACTION_DOWNLOAD_FINISH)
                .putExtra(URI_FINISH_KEY, Uri.fromFile(outputImageFile))
            sendBroadcast(intent)

            internalNotificationManager.createNotificationAfterJobDone(
                this@DownloadService,
                Uri.fromFile(outputImageFile)
            )

            stopForeground(false)
            stopSelf()
        }
    }

    private fun downloadFile(url: String, file: File) {
        val request = Request.Builder().url(url).build()
        val response = okHttpClient.newCall(request).execute()
        val body = response.body
        val responseCode = response.code

        if (responseCode >= HttpURLConnection.HTTP_OK &&
            responseCode < HttpURLConnection.HTTP_MULT_CHOICE &&
            body != null
        ) {

            val length = body.contentLength()
            body.byteStream().apply {
                file.outputStream().use { fileOut ->
                    var bytesCopied = 0
                    val buffer = ByteArray(BUFFER_LENGTH_BYTES)
                    var bytes = read(buffer)

                    val downloadingTitle = getString(R.string.downloading_title)

                    while (bytes >= 0) {
                        fileOut.write(buffer, 0, bytes)
                        bytesCopied += bytes
                        bytes = read(buffer)

                        val progress = ((bytesCopied * 100) / length).toInt()
                        internalNotificationManager.updateNotification(
                            this@DownloadService,
                            "$downloadingTitle $progress%",
                            progress
                        )

                        val intent = Intent(ACTION_DOWNLOAD_PROGRESS)
                            .putExtra(PROGRESS_VALUE, progress)
                        sendBroadcast(intent)

                    }
                }
            }
        }
    }

    private fun unZip(archiveFile: File, imageFile: File) {
        ZipFile(archiveFile).use { zip ->
            zip.getInputStream(zip.entries().nextElement()).use { input ->
                imageFile.outputStream().use { output ->
                    input.copyTo(output)
                }
            }
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
        const val URI_FINISH_KEY = "uri_finish_key"
        const val BUFFER_LENGTH_BYTES = 1024

        const val PROGRESS_VALUE = "PROGRESS_VALUE"
        const val ACTION_DOWNLOAD_PROGRESS =
            BuildConfig.APPLICATION_ID + "_ACTION_DOWNLOAD_PROGRESS"
        const val ACTION_DOWNLOAD_FINISH = BuildConfig.APPLICATION_ID + "_ACTION_DOWNLOAD_FINISH"
        const val ACTION_DOWNLOAD_ERROR = BuildConfig.APPLICATION_ID + "_ACTION_DOWNLOAD_ERROR"
    }
}