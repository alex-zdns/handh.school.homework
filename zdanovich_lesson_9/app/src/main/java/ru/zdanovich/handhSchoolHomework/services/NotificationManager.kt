package ru.zdanovich.handhSchoolHomework.services

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import ru.zdanovich.handhSchoolHomework.R
import ru.zdanovich.handhSchoolHomework.presenter.ShowImageActivity
import java.util.*

class NotificationManager {

    @RequiresApi(Build.VERSION_CODES.O)
    fun createNotificationForOreo(
        context: Context,
        title: String,
        pendingIntent: PendingIntent? = null
    ): Notification {
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(createChannel(context))

        return createNotification(context, title, pendingIntent)
    }


    fun createNotificationAfterJobDone(context: Context, resultFileUri: Uri) {
        val notifyIntent = Intent(context, ShowImageActivity::class.java)
            .apply {
                flags = (Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                putExtra(ShowImageActivity.KEY_IMAGE_URI, resultFileUri)
            }

        val notifyPendingIntent = PendingIntent.getActivity(
            context,
            REQUEST_CONTENT,
            notifyIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )


        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle("Загрузка и распаковка завершена")
            .setSmallIcon(R.drawable.ic_download)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setWhen(Date().time)
            .setContentIntent(notifyPendingIntent)

        NotificationManagerCompat.from(context)
            .notify(2, builder.build())
    }


    fun updateNotification(context: Context, progress: Int) {
        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setOngoing(true)
            .setSmallIcon(R.drawable.ic_download)
            .setContentTitle("Загрузка... $progress% из $MAX_PROGRESS%")
            .setProgress(MAX_PROGRESS, progress, false)
            .build()

        NotificationManagerCompat.from(context)
            .notify(NOTIFICATION_ID, notification)

    }

    private fun createNotification(
        context: Context,
        title: String,
        pendingIntent: PendingIntent? = null
    ): Notification {
        return NotificationCompat.Builder(context, CHANNEL_ID)
            .setOngoing(true)
            .setProgress(MAX_PROGRESS, INIT_PROGRESS, false)
            .setContentTitle(title)
            .setSmallIcon(R.drawable.ic_download)
            .also { notificationBuilder ->
                if (pendingIntent != null) {
                    notificationBuilder.setContentIntent(pendingIntent)
                }
            }
            .build()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createChannel(context: Context): NotificationChannel {
        return NotificationChannel(
            CHANNEL_ID,
            context.getString(R.string.download_service_channel_name),
            NotificationManager.IMPORTANCE_DEFAULT
        ).apply {
            description = context.getString(R.string.download_service_channel_description)
            setSound(null, null)
            enableVibration(false)
        }
    }

    companion object {
        const val NOTIFICATION_ID = 1
        const val CHANNEL_ID = "ChannelId"
        const val MAX_PROGRESS = 100
        const val INIT_PROGRESS = 0
        const val REQUEST_CONTENT = 1

    }
}