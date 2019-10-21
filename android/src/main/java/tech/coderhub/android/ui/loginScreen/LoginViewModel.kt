package tech.coderhub.android.ui.loginScreen

import androidx.lifecycle.MutableLiveData
import io.reactivex.rxkotlin.subscribeBy
import tech.coderhub.android.base.BaseViewModel
import tech.coderhub.android.network.ApiRequester
import tech.coderhub.android.network.NetworkError
import javax.inject.Inject

class LoginViewModel @Inject
constructor(private val apiRequester: ApiRequester) : BaseViewModel() {
    val userLiveData = MutableLiveData<User>()
    val tokenLiveData = MutableLiveData<Token>()

    fun getUserDetails() {
        disposable.add(apiRequester.userDetails.doOnSubscribe { isLoading.value = true }.doOnEvent { _, _ -> isLoading.value = false }.subscribeBy(
                onSuccess = {
                    if (it.id == null) {
                        serverResponseError.value = "Something went wrong"
                    } else {
                        userLiveData.value = it
                    }
                },
                onError = {
                    networkError.value = NetworkError.getServerResponseMessage(it)
                }))
    }

    fun getUserToken(userSubmit: UserSubmit) {
        disposable.add(apiRequester.getUserToken(userSubmit).doOnSubscribe { isLoading.value = true }.doOnEvent { _, _ -> isLoading.value = false }.subscribeBy(
                onSuccess = {
                    if (it.token == null) {
                        serverResponseError.value = "Something went wrong"
                    } else {
                        tokenLiveData.value = it
                    }
                },
                onError = {
                    networkError.value = NetworkError.getServerResponseMessage(it)
                }))
    }
}
