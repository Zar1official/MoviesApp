package ru.zar1official.moviesapp.data

import retrofit2.http.GET
import retrofit2.http.Query
import ru.zar1official.moviesapp.data.models.MoviesResponse

interface MoviesService {
    @GET(DataConstants.GET_MOVIES_ENDPOINT)
    suspend fun getMovies(@Query(DataConstants.OFFSET_QUERY) offset: Int): MoviesResponse
}