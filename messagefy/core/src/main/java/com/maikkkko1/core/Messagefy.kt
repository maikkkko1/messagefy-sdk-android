package com.maikkkko1.core

import android.content.Context
import android.util.Log
import com.maikkkko1.core.network.MessagefySocketEvents
import com.maikkkko1.core.network.MessagefySocketService
import com.maikkkko1.core.notification.MessagefyNotificationHandler
import com.maikkkko1.core.notification.MessagefyNotificationImpl

/**
 * Created by Maikon Ferreira on 05/12/20.
 */

class Messagefy {
    companion object {
        fun start(context: Context, messagefyNotificationImpl: MessagefyNotificationImpl) {
            MessagefyInitializer.context = context
            MessagefyInitializer.messagefyNotificationImpl = messagefyNotificationImpl

            MessagefyNotificationHandler.createNotificationChannel()

            MessagefySocketService.initSocketInstance()
            MessagefySocketService.connectToInstance()
        }

        fun setDeviceToken(deviceToken: String) {
            MessagefySocketService.socketInstance?.emit(MessagefySocketEvents.SUBSCRIBE_TO_NOTIFICATIONS, deviceToken)
        }

        fun requestNewDeviceToken() {
            MessagefySocketService.socketInstance?.emit(MessagefySocketEvents.REQUEST_NEW_DEVICE_TOKEN)
        }
    }
}