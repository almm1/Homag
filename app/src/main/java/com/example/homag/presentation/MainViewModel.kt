package com.example.homag.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.homag.domain.entity.AuthEntity
import com.example.homag.domain.entity.RegEntity
import com.example.homag.domain.repository.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository) : ViewModel() {

    val authLogin = mutableStateOf("")
    val authPassword = mutableStateOf("")

    val regName = mutableStateOf("")
    val regGender = mutableStateOf("")
    val regBirthday = mutableStateOf("")
    val regMobile = mutableStateOf("")
    val regEmail = mutableStateOf("")
    val regWorkCompany = mutableStateOf("")
    val regUfServiceNumber = mutableStateOf("")
    val regLogin = mutableStateOf("")
    val regPassword = mutableStateOf("")
    val regConfirmPassword = mutableStateOf("")

    val showDialog = MutableStateFlow(false)
    val message = mutableStateOf("")
    val authStatus = mutableStateOf(false)
    val regStatus = mutableStateOf("")


    private fun openDialog() {
        showDialog.value = true
    }

    fun onDialogConfirm() {
        showDialog.value = false
    }

    fun auth() {
        val authEntity = AuthEntity(login = authLogin.value, password = authPassword.value)
        viewModelScope.launch {
            val response = repository.auth(authEntity)
            if (response != null) {
                authStatus.value = response.status
                if (authStatus.value) showDialog("Авторизация успешна. ID  - ${response.id}")
                else showDialog(response.message)
            } else showDialog("error")
        }
    }


    private fun showDialog(msg: String) {
        message.value = msg
        openDialog()
    }

    fun reg() {
        val regEntity = RegEntity(
            name = regName.value,
            personalGender = regGender.value,
            personalBirthday = regBirthday.value,
            personalMobile = regMobile.value,
            email = regEmail.value,
            workCompany = regWorkCompany.value,
            ufServiceNumber = regUfServiceNumber.value,
            login = regLogin.value,
            password = regPassword.value,
            confirmPassword = regConfirmPassword.value
        )
        viewModelScope.launch {
            val response = repository.reg(regEntity)
            if (response != null) {
                regStatus.value = response.status.toString()
                if (regStatus.value == "TRUE") showDialog("Регистрация успешна. ID  - ${response.userId}")
                else showDialog(response.message.toString())
            } else showDialog("error")
        }
    }

    class ViewModelFactory(
        private val repository: Repository
    ) :
        ViewModelProvider.NewInstanceFactory() {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MainViewModel(
                repository = repository
            ) as T
        }
    }
}