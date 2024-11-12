package com.mzakialkhairi.techtest.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mzakialkhairi.techtest.data.model.LoginData
import com.mzakialkhairi.techtest.data.model.LoginResponse
import com.mzakialkhairi.techtest.data.model.RegisterData
import com.mzakialkhairi.techtest.data.model.RegisterResponse
import com.mzakialkhairi.techtest.data.network.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response


class AuthViewModel : ViewModel() {

    val email = MutableLiveData<String>()
    val username = MutableLiveData<String>()
    val password = MutableLiveData<String>()


    // MutableLiveData untuk observasi
    val isLoading = MutableLiveData<Boolean>()
    private val _loginResult = MutableLiveData<String>()
    val loginResult: LiveData<String> get() = _loginResult

    private val _registerResult = MutableLiveData<String>()
    val registerResult: LiveData<String> get() = _registerResult




    fun register() {
        val email = email.value?.trim()
        val username = username.value?.trim()
        val password = password.value?.trim()
        val registerResult = MutableLiveData<String>()

        // Validasi input
        if (email.isNullOrEmpty() || username.isNullOrEmpty() || password.isNullOrEmpty()) {
            registerResult.value = "Email, Username, dan Password tidak boleh kosong"
            return
        }
        val registerRequest = RegisterData(email, username, password)

        // Menggunakan coroutine untuk API call
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response: Response<RegisterResponse> = RetrofitClient.instance.register(registerRequest)
                if (response.isSuccessful) {
                    registerResult.postValue(response.body()?.message)
                } else {
                    registerResult.postValue("Registrasi gagal: ${response.message()}")
                }
            } catch (e: Exception) {
                registerResult.postValue("Terjadi kesalahan: ${e.message}")
            }
        }
    }
    // Fungsi Login
    fun login(email: String?, password: String?) {
        val trimmedEmail = email?.trim()
        val trimmedPassword = password?.trim()
        val loginResult = MutableLiveData<String>()

        // Validasi input
        if (trimmedEmail.isNullOrEmpty() || trimmedPassword.isNullOrEmpty()) {
            loginResult.value = "Email dan Password tidak boleh kosong"
            return
        }

        // Menyiapkan data untuk request
        val loginRequest = LoginData(trimmedEmail, trimmedPassword)

        // Menjalankan coroutine untuk API call
        isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response: Response<LoginResponse> = RetrofitClient.instance.login(loginRequest)
                // Mengirim hasil respons ke UI thread
                if (response.isSuccessful) {
                    val token = response.body()?.data?.token
                    loginResult.postValue("Login berhasil, Token: $token")
                } else {
                    loginResult.postValue("Login gagal: ${response.message()}")
                }
            } catch (e: Exception) {
                loginResult.postValue("Terjadi kesalahan: ${e.message}")
            } finally {
                isLoading.postValue(false)
            }
        }
    }

}
