package ru.zar1official.moviesapp.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.zar1official.moviesapp.data.models.MovieEntity
import ru.zar1official.moviesapp.domain.Repository
import javax.inject.Inject

class MoviesRepository @Inject constructor(private val service: MoviesService) : Repository {
    override fun getMovies(): Flow<PagingData<MovieEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                MoviesPagingSource(service)
            }
        ).flow
    }
}