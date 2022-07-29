package ru.zar1official.moviesapp.data.models

import com.google.gson.annotations.SerializedName

data class MovieEntity(
    @SerializedName("display_title")
    val title: String,
    @SerializedName("multimedia")
    val image: MovieImageEntity,
    @SerializedName("summary_short")
    val description: String
)