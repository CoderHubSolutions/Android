package tech.coderhub.android.network;


import tech.coderhub.android.ui.forgotPasswordScreen.PasswordSubmit;
import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import tech.coderhub.android.ui.forgotPasswordScreen.ForgotPassword;
import tech.coderhub.android.ui.forgotPasswordScreen.PasswordChangeResult;
import tech.coderhub.android.ui.loginScreen.Token;
import tech.coderhub.android.ui.loginScreen.User;
import tech.coderhub.android.ui.loginScreen.UserSubmit;
import tech.coderhub.android.ui.profileDetailsScreen.Profile;

import static tech.coderhub.android.helper.AppHelperKt.getIpFromAmazon;

public class ApiRequester {
    private final Api api;

    @Inject
    public ApiRequester(Api api) {
        this.api = api;
    }

    public Single<Token> getUserToken(UserSubmit userSubmit) {
        return api.getUserToken(userSubmit)
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<User> getUserDetails() {
        return api.getUserDetails()
                .observeOn(AndroidSchedulers.mainThread());
    }


    public Single<Profile> getProfile() {
        return api.getProfile()
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<String> getIp() {
        return Single
                .create((SingleOnSubscribe<String>) emitter -> {
                    String ip = getIpFromAmazon();
                    emitter.onSuccess(ip);
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }



    public Single<ForgotPassword> sendPinToEmail(ForgotPassword forgotPassword) {
        return api.sendPinToEmail(forgotPassword)
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<PasswordChangeResult> changePassword(PasswordSubmit passwordSubmit) {
        return api.changePassword(passwordSubmit)
                .observeOn(AndroidSchedulers.mainThread());
    }
}
