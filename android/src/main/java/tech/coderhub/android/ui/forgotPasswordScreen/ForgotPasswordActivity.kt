package tech.coderhub.android.ui.forgotPasswordScreen

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import tech.coderhub.android.helper.getDeviceId

import kotlinx.android.synthetic.main.activity_forgot_password.*
import kotlinx.android.synthetic.main.app_bar_layout.*
import kotlinx.android.synthetic.main.progressbar.*
import org.jetbrains.anko.clearTop
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast
import tech.coderhub.android.MainActivity
import tech.coderhub.android.R
import tech.coderhub.android.base.BaseActivity
import tech.coderhub.android.base.showIndicator
import tech.coderhub.android.helper.Constants
import tech.coderhub.android.ui.loginScreen.User

class ForgotPasswordActivity : BaseActivity() {
    private lateinit var forgotPasswordViewModel: ForgotPasswordViewModel

    override fun layoutRes() = R.layout.activity_forgot_password

    private var forgotId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        progressBar.bringToFront()
        forgotPasswordViewModel = ViewModelProviders.of(this, viewModelFactory).get(
            ForgotPasswordViewModel::class.java)
        observeViewModel()
        initField()
    }

    private fun initField() {
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            title = "PasswordSubmit Recovery"
            setDisplayHomeAsUpEnabled(true)
        }
        pin.visibility = View.GONE
        arrayOf(pin, password, confirmPassword, submitPassword).forEach {
            it.visibility = View.GONE
        }
        submitEmail.setOnClickListener {
            forgotPasswordViewModel.sendPinToEmail(ForgotPassword(email.text.toString(), getDeviceId()))
        }

        submitPassword.setOnClickListener {
            forgotPasswordViewModel.changePassword(PasswordSubmit(password.text.toString(), pin.text.toString(), forgotId))
        }
    }

    private fun observeViewModel() {
        forgotPasswordViewModel.isLoading.observe(this, Observer<Boolean> { showIndicator(it) })
        forgotPasswordViewModel.networkError.observe(this, Observer<String>{ errorMessage -> toast(errorMessage) })
        forgotPasswordViewModel.serverResponseError.observe(this, Observer<String>{ errorMessage -> toast(errorMessage) })
        forgotPasswordViewModel.forgetPasswordLiveData.observe(this, Observer<ForgotPassword> {
            if (it.id==-1) toast("User Is Not registered")
            else {
                forgotId = it.id
                email.visibility = View.GONE
                submitEmail.visibility = View.GONE
                pin.visibility = View.VISIBLE
                password.visibility = View.VISIBLE
                confirmPassword.visibility = View.VISIBLE
                submitPassword.visibility = View.VISIBLE
            }
        })
        forgotPasswordViewModel.userLiveData.observe(this, Observer<User> {
            saveUserData(it)
        })
        forgotPasswordViewModel.tokenLiveData.observe(this, Observer<String> {
            cache!!.putValue(Constants.ACCESS_TOKEN, it)
            startActivity(intentFor<MainActivity>().clearTop())
        })
    }

    private fun saveUserData(it: User) {
        with(cache!!) {
            putValue(Constants.ID, it.id)
            putValue(Constants.USERNAME, it.username)
            putValue(Constants.OBJECT_ID, it.objectId)
            putValue(Constants.USER_TYPE, it.userType)
            putValue(Constants.STATUS_ID, it.statusId)
            putValue(Constants.SUPERVISOR_ID, it.supervisorId)
            putValue(Constants.EMAIL, it.email)
            putValue(Constants.EMAIL_VERIFIED_AT, it.emailVerifiedAt)
            putValue(Constants.STATUS, it.status)
        }
    }
}
