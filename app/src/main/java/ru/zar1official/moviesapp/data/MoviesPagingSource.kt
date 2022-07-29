package ru.zar1official.moviesapp.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ru.zar1official.moviesapp.data.models.MovieEntity

class MoviesPagingSource(private val moviesService: MoviesService) :
    PagingSource<Int, MovieEntity>() {

    override fun getRefreshKey(state: PagingState<Int, MovieEntity>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieEntity> {
        val offset = params.key ?: DEFAULT_OFFSET
        return try {
            val response = moviesService.getMovies(offset)
            val movies = response.results
            LoadResult.Page(
                data = movies,
                prevKey = if (offset == DEFAULT_OFFSET) null else offset - 20,
                nextKey = if (movies.isEmpty()) null else offset + 20
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    companion object {
        private const val DEFAULT_OFFSET = 0
    }
}