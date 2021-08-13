package com.work.list.data.network

import com.work.list.models.ItemsResponse
import retrofit2.http.GET

interface ApiService {

    @GET(".")
    suspend fun getItems(): ItemsResponse

}