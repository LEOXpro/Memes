package network

import network.model.LoginRequest
import network.model.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ServerApi {
    @POST("auth/login")
    fun autorization(@Body data: LoginRequest): Call<LoginResponse>


}