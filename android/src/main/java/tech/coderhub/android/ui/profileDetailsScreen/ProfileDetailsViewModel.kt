package tech.coderhub.android.ui.profileDetailsScreen

import androidx.lifecycle.MutableLiveData
import io.reactivex.rxkotlin.subscribeBy
import tech.coderhub.android.base.BaseViewModel
import tech.coderhub.android.network.ApiRequester
import tech.coderhub.android.network.NetworkError
import tech.coderhub.android.ui.profileDetailsScreen.Profile
import javax.inject.Inject

class ProfileDetailsViewModel @Inject
constructor(private val apiRequester: ApiRequester) : BaseViewModel() {
    val profileLiveData = MutableLiveData<Profile>()
    fun getProfile() {
        disposable.add(apiRequester.profile.doOnSubscribe { isLoading.value = true }.doOnEvent { _, _ -> isLoading.value = false }.subscribeBy(
                onSuccess = {
                    profileLiveData.value = it
                },
                onError = {
                    networkError.value = NetworkError.getServerResponseMessage(it)
                }))
    }
}