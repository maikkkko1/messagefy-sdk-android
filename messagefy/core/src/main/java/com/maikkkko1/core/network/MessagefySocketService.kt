package com.maikkkko1.core.network

import android.util.Log
import com.google.gson.Gson
import com.maikkkko1.core.MessagefyInitializer
import com.maikkkko1.core.MessagefySettings
import com.maikkkko1.core.entities.MessagefyNotification
import com.maikkkko1.core.notification.MessagefyNotificationImpl
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter
import org.json.JSONObject
import java.net.URISyntaxException


/**
 * Created by Maikon Ferreira on 05/12/20.
 */

object MessagefySocketService {
    var socketInstance: Socket? = null

    fun initSocketInstance() {
        try {
            socketInstance = IO.socket(MessagefySettings.SERVER_URL).let {
                setCallbacks(it)
                it
            }
        } catch (e: URISyntaxException) {
            Log.e("MESSAGEFY_SERVICE", e.message ?: "Error")
        }
    }

    fun connectToInstance() {
        socketInstance?.connect()
    }

    private fun setCallbacks(instance: Socket) {
        instance.on(Socket.EVENT_CONNECT, onConnect);

        instance.on(MessagefySocketEvents.DEVICE_TOKEN_RECEIVED) { args ->
            MessagefyInitializer.messagefyNotificationImpl.run {
                onNewDeviceTokenReceived((args[0] as? JSONObject)?.get("deviceToken") as? String ?: "")
            }
        }

        instance.on(MessagefySocketEvents.NOTIFICATION_RECEIVED) { args ->
            MessagefyInitializer.messagefyNotificationImpl.run {
                instance.emit(MessagefySocketEvents.CONFIRM_NOTIFICATION_RECEIVED, true)
                val notification = Gson().fromJson(args[0].toString(), MessagefyNotification::class.java)

                onNotificationReceived(notification)
            }
        }
    }

    private val onConnect: Emitter.Listener = Emitter.Listener {
        Log.i("MESSAGEFY_SERVICE", "Connected!")
    }
}