package ru.lbas.memes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import ru.lbas.memes.network.NetworkService
import ru.lbas.memes.network.ServerApi
import ru.lbas.memes.network.model.LoginRequest
import ru.lbas.memes.network.model.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthActivity : AppCompatActivity() {
    private lateinit var authBtn: Button
    private lateinit var tokenView: TextView
    private lateinit var progressBar: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        authBtn = findViewById(R.id.b_buttonlogin)
        progressBar = findViewById(R.id.progress_bar)
        authBtn.setOnClickListener {
            authorization()
            Handler().postDelayed({ progressBarNo() }, 300)
            }
        tokenView = findViewById(R.id.tv_tokenview)

    }
    fun progressBarView(){
        progressBar.setVisibility(ProgressBar.VISIBLE)
    }
    fun progressBarNo(){
        progressBar.setVisibility(ProgressBar.INVISIBLE)
    }

    private fun authorization() {
        val retroServise = NetworkService.createInstance().create(ServerApi::class.java)
        progressBarView()

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
