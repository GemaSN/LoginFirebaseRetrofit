package com.example.loginfirebaseretrofit.login.ui

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel() : ViewModel() {
    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _chkState = MutableLiveData<Boolean>()
    val chkState: LiveData<Boolean> = _chkState

    private val _isLoginEnabled = MutableLiveData<Boolean>()
    val isLoginEnabled: LiveData<Boolean> = _isLoginEnabled

    fun toggleCheck() {
        _chkState.value = !_chkState.value
    }

    /**
     * La contraseña debe tener una longitud mínima de 4 caracteres
     * y el correo debe tener formato "a@a.a"
     */
    private fun validCredentials() {
        _isLoginEnabled.value = _password.value.length > 3 &&
                Patterns.EMAIL_ADDRESS.matcher(_email.value).matches()
    }

    fun onLoginChange(newMail: String, newPass: String) {
        _email.value = newMail
        _password.value = newPass
        validCredentials()
    }

    fun validateUser(email: String, password: String) = true
}
