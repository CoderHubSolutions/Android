package tech.coderhub.android.ui.forgotPasswordScreen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ForgotPassword {

    @SerializedName("username_or_email")
    @Expose
    String usernameOrEmail;

    @SerializedName("device_id")
    @Expose
    String deviceId;

    @SerializedName("id")
    @Expose
    Integer id;

    public ForgotPassword(String usernameOrEmail, String deviceId) {
        this.usernameOrEmail = usernameOrEmail;
        this.deviceId = deviceId;
    }

    public String getUsernameOrEmail() {
        return usernameOrEmail;
    }

    public void setUsernameOrEmail(String usernameOrEmail) {
        this.usernameOrEmail = usernameOrEmail;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
