package com.example.homag.data.repository

import com.example.homag.data.api.Api
import com.example.homag.domain.entity.AuthEntity
import com.example.homag.domain.entity.RegEntity
import com.example.homag.domain.entity.ResponseAuthEntity
import com.example.homag.domain.entity.ResponseRegEntity
import com.example.homag.domain.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RepositoryImpl(private val networkService: Api) : Repository {
    override suspend fun auth(authEntity: AuthEntity): ResponseAuthEntity? {
        return withContext(Dispatchers.IO) {
            try {
                val response =
                    networkService.auth(
                        login = authEntity.login,
                        password = authEntity.password
                    ).execute()
                val body = response.body()
                if (response.isSuccessful) {
                    return@withContext body?.let {
                        ResponseAuthEntity(
                            status = it.status,
                            message = body.message,
                            id = body.id
                        )
                    }
                } else {
                    return@withContext body?.let {
                        ResponseAuthEntity(
                            status = it.status,
                            message = body.message,
                            id = body.id
                        )
                    }
                }
            } catch (e: Exception) {
                return@withContext null
            }
        }
    }

    override suspend fun reg(regEntity: RegEntity): ResponseRegEntity? {
        return withContext(Dispatchers.IO) {
            try {
                val response = networkService.reg(
                    name = regEntity.name,
                    gender = regEntity.personalGender,
                    birthday = regEntity.personalBirthday,
                    mobile = regEntity.personalMobile,
                    company = regEntity.workCompany,
                    email = regEntity.email,
                    serviceNumber = regEntity.ufServiceNumber,
                    login = regEntity.login,
                    password = regEntity.password,
                    confirmPassword = regEntity.confirmPassword
                ).execute()
                val body = response.body()
                if (response.isSuccessful) {
                    return@withContext body?.let {
                        ResponseRegEntity(
                            status = it.STATUS,
                            userId = it.USERID,
                            message = it.MESSAGE
                        )
                    }
                } else {
                    body?.let {
                        ResponseRegEntity(
                            status = it.STATUS,
                            userId = it.USERID,
                            message = it.MESSAGE
                        )
                    }
                }
            } catch (e: Exception) {
                return@withContext null
            }
        }
    }
}