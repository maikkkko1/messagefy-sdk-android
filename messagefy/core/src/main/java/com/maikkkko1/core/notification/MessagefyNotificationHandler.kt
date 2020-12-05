package com.maikkkko1.core.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.maikkkko1.core.MessagefyInitializer
import com.maikkkko1.core.MessagefySettings
import com.maikkkko1.core.R
import com.maikkkko1.core.entities.MessagefyNotification

/**
 * Created by Maikon Ferreira on 05/12/20.
 */

class MessagefyNotificationHandler {
    companion object {
        fun showNotification(notification: MessagefyNotification, onClickedRedirectTo: Class<*>? = null) {
            val applicationContext = MessagefyInitializer.context

            var notificationBuilder = NotificationCompat.Builder(
                applicationContext,
                MessagefySettings.NOTIFICATION_CHANNEL_ID
            )
                .setContentTitle(notification.title)
                .setContentText(notification.message)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setAutoCancel(notification.autoCancel ?: false)
                .setSmallIcon(notification.icon ?: R.drawable.ic_bell)

            if (onClickedRedirectTo != null) {
                val intent = Intent(applicationContext, onClickedRedirectTo).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                }

                val pendingIntent: PendingIntent = PendingIntent.getActivity(applicationContext, 0, intent, 0)

                notificationBuilder.setContentIntent(pendingIntent)
                notificationBuilder.setAutoCancel(true)
            }

            with(NotificationManagerCompat.from(applicationContext)) {
                notify(notification.id ?: 1, notificationBuilder.build())
            }
        }

        fun createNotificationChannel() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val name = MessagefySettings.NOTIFICATION_CHANNEL_NAME
                val descriptionText = MessagefySettings.NOTIFICATION_CHANNEL_DESCRIPTION
                val importance = NotificationManager.IMPORTANCE_HIGH
                val channel = NotificationChannel(
                    MessagefySettings.NOTIFICATION_CHANNEL_ID,
                    name,
                    importance
                ).apply {
                    description = descriptionText
                }

                val notificationManager: NotificationManager =
                    MessagefyInitializer.context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

                notificationManager.createNotificationChannel(channel)
            }
        }
    }
}