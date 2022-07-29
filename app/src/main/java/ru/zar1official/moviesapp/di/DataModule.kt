package ru.zar1official.moviesapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.zar1official.moviesapp.data.MoviesRepository
import ru.zar1official.moviesapp.domain.Repository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    @Singleton
    fun bindRepository(repositoryImpl: MoviesRepository): Repository
}