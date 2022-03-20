package com.example.homag.domain.entity


data class RegEntity(
    val name: String,
    val personalGender: String,
    val personalBirthday: String,
    val personalMobile: String,
    val email: String,
    val workCompany: String,
    val ufServiceNumber: String,
    val login: String,
    val password: String,
    val confirmPassword: String
)
