package ru.lbas.memes

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import network.NetworkService
import network.ServerApi
import network.model.LoginRequest
import network.model.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity: AppCompatActivity() {
    private lateinit var authBtn: Button
    private lateinit var tokenView: TextView
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        authBtn = findViewById(R.id.b_buttonlogin)
        authBtn.setOnClickListener { authorization() }
        tokenView = findViewById(R.id.tv_tokenview)
    }
    private fun authorization() {
        val retroServise = NetworkService.createInstance().create(ServerApi::class.java)
        retroServise.autorization(LoginRequest("login", "password"))
            .enqueue(object : Callback<LoginResponse> {
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                }

                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    tokenView.text = response.body()?.accessToken
                }

            })

    }
}