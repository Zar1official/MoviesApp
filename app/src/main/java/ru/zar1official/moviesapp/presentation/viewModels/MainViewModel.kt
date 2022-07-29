package ru.zar1official.moviesapp.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.zar1official.moviesapp.data.models.MovieEntity
import ru.zar1official.moviesapp.domain.Repository
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    val movies: LiveData<PagingData<MovieEntity>> = repository.getMovies().cachedIn(viewModelScope)
}