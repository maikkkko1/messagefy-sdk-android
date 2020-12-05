package com.maikkkko1.core.notification

import com.maikkkko1.core.entities.MessagefyNotification

/**
 * Created by Maikon Ferreira on 05/12/20.
 */

open class MessagefyNotificationImpl {
    open fun onNotificationReceived(notification: MessagefyNotification) {}

    open fun onNewDeviceTokenReceived(deviceToken: String) {}
}