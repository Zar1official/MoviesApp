package ru.zar1official.moviesapp.data

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import ru.zar1official.moviesapp.data.models.MovieEntity
import ru.zar1official.moviesapp.domain.Repository
import javax.inject.Inject

class MoviesRepository @Inject constructor(private val service: MoviesService) : Repository {
    override fun getMovies(): LiveData<PagingData<MovieEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                MoviesPagingSource(service)
            }
        ).liveData
    }
}