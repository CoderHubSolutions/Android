package tech.coderhub.android.ui.loginScreen

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import tech.coderhub.android.ui.forgotPasswordScreen.ForgotPasswordActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.clearTop
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import tech.coderhub.android.MainActivity
import tech.coderhub.android.R
import tech.coderhub.android.base.BaseActivity
import tech.coderhub.android.base.showIndicator
import tech.coderhub.android.helper.Constants

class LoginActivity : BaseActivity() {
    override fun layoutRes() = R.layout.activity_login
    var isUserLoggedIn = false
    private lateinit var loginViewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        retryLayout.setOnClickListener {
            retryLayout.visibility = View.GONE
            init()
        }
        observeViewModel()
    }

    private fun init() {
        progressBar.bringToFront()
        loginViewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel::class.java)
        if(cache!!.getValue(Constants.ACCESS_TOKEN)!="") {
            loginLayout.visibility = View.GONE
            isUserLoggedIn = true
            loginViewModel.getUserDetails()
        }
        loginButton.setOnClickListener {
            loginViewModel.getUserToken(UserSubmit(email.text.toString(), password.text.toString()))
        }
        forgotPassword.setOnClickListener { startActivity<ForgotPasswordActivity>() }
    }

    private fun observeViewModel() {
        loginViewModel.isLoading.observe(this, Observer<Boolean> { showIndicator(it) })
        loginViewModel.networkError.observe(this, Observer<String>{ errorMessage ->
            if (isUserLoggedIn) {
                retryLayout.visibility = View.VISIBLE
            }
            toast(errorMessage)
        })
        loginViewModel.serverResponseError.observe(this, Observer<String>{
            errorMessage -> toast(errorMessage)
            loginLayout.visibility = View.VISIBLE
        })
        loginViewModel.tokenLiveData.observe(this, Observer { getUserDetails(it) })
        loginViewModel.userLiveData.observe(this, Observer<User> { saveUserDetails(it) })
    }

    private fun getUserDetails(token: Token) {
        cache!!.putValue(Constants.ACCESS_TOKEN, token.token)
        loginViewModel.getUserDetails()
    }

    private fun saveUserDetails(user: User) {
        with(cache!!) {
            putValue(Constants.ID, user.id)
            putValue(Constants.USERNAME, user.username)
            putValue(Constants.OBJECT_ID, user.objectId)
            putValue(Constants.USER_TYPE, user.userType)
            putValue(Constants.STATUS_ID, user.statusId)
            putValue(Constants.SUPERVISOR_ID, user.supervisorId)
            putValue(Constants.EMAIL, user.email)
            putValue(Constants.EMAIL_VERIFIED_AT, user.emailVerifiedAt)
            putValue(Constants.STATUS, user.status)
        }
        startActivity(intentFor<MainActivity>().clearTop())
        finish()
    }
}
