package network.model

import com.google.gson.annotations.SerializedName

data class LoginResponse (
    @SerializedName("accessToken") val accessToken: String
)
