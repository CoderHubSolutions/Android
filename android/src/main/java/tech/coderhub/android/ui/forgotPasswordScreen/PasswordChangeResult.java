package tech.coderhub.android.ui.forgotPasswordScreen;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import tech.coderhub.android.ui.loginScreen.User;

public class PasswordChangeResult {

    @SerializedName("user")
    @Expose
    User user;

    @SerializedName("token")
    @Expose
    String token;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
