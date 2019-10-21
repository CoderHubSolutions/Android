package tech.coderhub.android.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel: ViewModel() {
    val isLoading = MutableLiveData<Boolean>()
    val networkError = MutableLiveData<String>()
    val serverResponseError = MutableLiveData<String>()
    val disposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
        disposable.dispose()
    }
}