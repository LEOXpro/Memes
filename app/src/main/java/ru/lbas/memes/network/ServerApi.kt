package ru.lbas.memes.network

import ru.lbas.memes.network.model.LoginRequest
import ru.lbas.memes.network.model.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ServerApi {
    @POST("auth/login")
    fun autorization(@Body data: LoginRequest): Call<LoginResponse>


}