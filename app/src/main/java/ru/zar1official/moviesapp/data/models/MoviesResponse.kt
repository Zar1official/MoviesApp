package ru.zar1official.moviesapp.data.models

import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    @SerializedName("results")
    val results: List<MovieEntity>
)