package ru.zar1official.moviesapp.domain

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.zar1official.moviesapp.data.models.MovieEntity

interface Repository {
    fun getMovies(): LiveData<PagingData<MovieEntity>>
}