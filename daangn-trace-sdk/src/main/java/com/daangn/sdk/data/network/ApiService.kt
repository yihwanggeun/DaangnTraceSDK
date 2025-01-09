package com.daangn.sdk.data.network

import com.daangn.sdk.data.model.Event
import com.daangn.sdk.data.model.EventResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("metrics/events")
    suspend fun sendEvents(@Body events: List<Event>): EventResponse
}