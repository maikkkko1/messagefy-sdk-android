package com.maikkkko1.core.network

/**
 * Created by Maikon Ferreira on 05/12/20.
 */

class MessagefySocketEvents {
    companion object {
        const val NOTIFICATION_RECEIVED = "new:notification"
        const val DEVICE_TOKEN_RECEIVED = "new:device_token"

        const val CONFIRM_NOTIFICATION_RECEIVED = "request:confirm_received"
        const val REQUEST_NEW_DEVICE_TOKEN = "request:new_token"

        const val SUBSCRIBE_TO_NOTIFICATIONS = "join:notifications"
    }
}