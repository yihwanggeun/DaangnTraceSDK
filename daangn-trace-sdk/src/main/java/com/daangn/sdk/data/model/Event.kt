package com.daangn.sdk.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Event(
    val type: String,
    val timestamp: Long,
    val metadata: Map<String, String> = emptyMap()
)

@Serializable
data class EventResponse(
    val message: String,
    val count: Int,
    val timestamp: String
)