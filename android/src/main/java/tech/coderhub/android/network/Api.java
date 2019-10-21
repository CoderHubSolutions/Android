package tech.coderhub.android.network;

import tech.coderhub.android.ui.forgotPasswordScreen.PasswordSubmit;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

import tech.coderhub.android.ui.forgotPasswordScreen.ForgotPassword;
import tech.coderhub.android.ui.forgotPasswordScreen.PasswordChangeResult;
import tech.coderhub.android.ui.loginScreen.Token;
import tech.coderhub.android.ui.loginScreen.User;
import tech.coderhub.android.ui.loginScreen.UserSubmit;
import tech.coderhub.android.ui.profileDetailsScreen.Profile;

public interface Api {

    @POST("/api/login")
    Single<Token> getUserToken(@Body UserSubmit userSubmit);

    @POST("/api/user/details")
    Single<User> getUserDetails();

    @GET("/api/employee/profile/")
    Single<Profile> getProfile();

    @POST("/api/forgot")
    Single<ForgotPassword> sendPinToEmail(@Body ForgotPassword email);

    @POST("/api/forgot/change-password")
    Single<PasswordChangeResult> changePassword(@Body PasswordSubmit passwordSubmit);

}
