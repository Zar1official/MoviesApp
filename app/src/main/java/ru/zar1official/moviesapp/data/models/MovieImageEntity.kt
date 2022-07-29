package ru.zar1official.moviesapp.data.models

import com.google.gson.annotations.SerializedName

data class MovieImageEntity(
    @SerializedName("src")
    val link: String
)