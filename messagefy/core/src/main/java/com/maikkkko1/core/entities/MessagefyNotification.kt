package com.maikkkko1.core.entities

/**
 * Created by Maikon Ferreira on 05/12/20.
 */

data class MessagefyNotification(
    val id: Int?,
    val title: String?,
    val message: String?,

    var icon: Int? = null,
    var autoCancel: Boolean? = false
)