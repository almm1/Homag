package com.example.homag.domain.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.homag.data.model.AuthResponse
import com.example.homag.domain.entity.AuthEntity
import com.example.homag.domain.entity.RegEntity
import com.example.homag.domain.entity.ResponseAuthEntity
import com.example.homag.domain.entity.ResponseRegEntity

interface Repository {
    suspend fun auth(authEntity: AuthEntity): ResponseAuthEntity?
    suspend fun reg(regEntity: RegEntity): ResponseRegEntity?
}