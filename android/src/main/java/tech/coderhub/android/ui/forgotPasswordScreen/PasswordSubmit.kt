package tech.coderhub.android.ui.forgotPasswordScreen

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PasswordSubmit(
        @SerializedName("password")
        @Expose
        var password: String = "",

        @SerializedName("pin")
        @Expose
        var pin: String = "",

        @SerializedName("id")
        @Expose
        var id: Int = 0
)
