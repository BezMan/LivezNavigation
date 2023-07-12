package com.example.liveznav.data

import com.google.gson.annotations.SerializedName

data class CountriesPerName(
    @SerializedName("country")
    val list: List<Country>,
    val name: String
)

data class Country(
    val country_id: String,
    val probability: Double
)

