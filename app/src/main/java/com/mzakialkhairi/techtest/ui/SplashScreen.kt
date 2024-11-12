package com.mzakialkhairi.techtest.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mzakialkhairi.techtest.MainActivity
import com.mzakialkhairi.techtest.data.SharedPrefsHelper

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Membuat instance SharedPreferencesHelper untuk mengecek token
        val sharedPreferencesHelper = SharedPrefsHelper
        val token = sharedPreferencesHelper.getToken(this)

        if (token != null) {
            // Jika token ada, pindah ke MainActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        } else {
            // Jika token tidak ada, pindah ke AuthActivity
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        // Menutup SplashScreen agar tidak bisa kembali ke sini
        finish()
    }

}