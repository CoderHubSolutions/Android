package tech.coderhub.android.ui.profileDetailsScreen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Profile {
    @SerializedName("type")
    @Expose
    String type;

    @SerializedName("full_name")
    @Expose
    String fullName;

    @SerializedName("phone")
    @Expose
    String phone;

    @SerializedName("email")
    @Expose
    String email;

    @SerializedName("photo_url")
    @Expose
    String photoUrl;

    @SerializedName("designation")
    @Expose
    String designation;

    @SerializedName("address")
    @Expose
    String address;

    @SerializedName("supervisor")
    @Expose
    String supervisor;

}
