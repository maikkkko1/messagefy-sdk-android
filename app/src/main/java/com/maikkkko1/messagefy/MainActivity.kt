package com.maikkkko1.messagefy

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.maikkkko1.core.Messagefy
import com.maikkkko1.core.MessagefySettings
import com.maikkkko1.core.entities.MessagefyNotification
import com.maikkkko1.core.notification.MessagefyNotificationHandler
import com.maikkkko1.core.notification.MessagefyNotificationImpl

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Messagefy.start(this, object: MessagefyNotificationImpl() {
            override fun onNewDeviceTokenReceived(deviceToken: String) {
                Log.i("MESSAGEFY_SERVICE", "TOKEN: ${deviceToken}")
            }

            override fun onNotificationReceived(notification: MessagefyNotification) {
                MessagefyNotificationHandler.showNotification(notification, MainActivity::class.java)
            }
        })

//        Messagefy.requestNewDeviceToken()
        Messagefy.setDeviceToken("4d372445-0139-433a-ba70-14f289286bf4")
    }

}