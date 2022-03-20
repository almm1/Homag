package com.example.homag.di

import com.example.homag.data.api.NetworkService
import com.example.homag.data.repository.RepositoryImpl


object DependencyInjection {
    private val networkModule by lazy {
        NetworkService
    }

    @Volatile
    var repository: RepositoryImpl? = null

    fun provideRepository(): RepositoryImpl {
        synchronized(this) {
            return repository ?: createRepository()
        }
    }

    private fun createRepository(): RepositoryImpl {
        val newRepo =
            RepositoryImpl(networkModule.retrofit)
        repository = newRepo
        return newRepo
    }
}