package com.work.list.models

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Keep
data class Items(
    @Json(name = "name")
    val name: String?,
    @Json(name = "price")
    val price: String?,
    @Json(name = "extra")
    val extra: String?
)

@JsonClass(generateAdapter = true)
@Keep
data class ItemsResponse(
    @Json(name = "status")
    val status: String?,
    @Json(name = "error")
    val error: String?,
    @Json(name = "data")
    val dataItem: DataItem?

)

@JsonClass(generateAdapter = true)
@Keep
data class DataItem(
    @Json(name = "items")
    val items: List<Items>?,
)

