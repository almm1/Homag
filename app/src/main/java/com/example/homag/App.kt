package com.example.homag

import android.app.Application
import com.example.homag.data.repository.RepositoryImpl
import com.example.homag.di.DependencyInjection

class App:Application() {
     val repository: RepositoryImpl
        get() = DependencyInjection.provideRepository()
}