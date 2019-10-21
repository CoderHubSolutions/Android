package tech.coderhub.android.ui.forgotPasswordScreen

import androidx.lifecycle.MutableLiveData
import io.reactivex.rxkotlin.subscribeBy
import tech.coderhub.android.base.BaseViewModel
import tech.coderhub.android.network.ApiRequester
import tech.coderhub.android.network.NetworkError
import tech.coderhub.android.ui.loginScreen.User

import javax.inject.Inject

class ForgotPasswordViewModel @Inject
constructor(private val apiRequester: ApiRequester) : BaseViewModel() {
    val forgetPasswordLiveData = MutableLiveData<ForgotPassword>()
    val userLiveData = MutableLiveData<User>()
    val tokenLiveData = MutableLiveData<String>()

    fun sendPinToEmail(forgotPassword: ForgotPassword) {
        disposable.add(apiRequester.sendPinToEmail(forgotPassword).doOnSubscribe { isLoading.value = true }.doOnEvent { _, _ -> isLoading.value = false }.subscribeBy(
                onSuccess = {
                    forgetPasswordLiveData.value = it
                },
                onError = {
                    networkError.value = NetworkError.getServerResponseMessage(it)
                }))
    }

    fun changePassword(passwordSubmit: PasswordSubmit) {
        disposable.add(apiRequester.changePassword(passwordSubmit).doOnSubscribe { isLoading.value = true }.doOnEvent { _, _ -> isLoading.value = false }.subscribeBy(
                onSuccess = {
                    userLiveData.value = it.user
                    tokenLiveData.value = it.token
                },
                onError = {
                    networkError.value = NetworkError.getServerResponseMessage(it)
                }))
    }
}