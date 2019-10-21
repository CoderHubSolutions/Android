package tech.coderhub.android.ui.profileDetailsScreen

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import kotlinx.android.synthetic.main.app_bar_layout.*
import kotlinx.android.synthetic.main.progressbar.*
import org.jetbrains.anko.toast
import tech.coderhub.android.R
import tech.coderhub.android.base.BaseActivity
import tech.coderhub.android.base.showIndicator

class ProfileDetailsActivity : BaseActivity() {
    lateinit var profileDetailsViewModel: ProfileDetailsViewModel
    override fun layoutRes() = R.layout.activity_profile_details
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        progressBar.bringToFront()
        profileDetailsViewModel = ViewModelProviders.of(this, viewModelFactory).get(
            ProfileDetailsViewModel::class.java)
        initField()
        observeViewModel()
        profileDetailsViewModel.getProfile()
    }

    private fun observeViewModel() {
        profileDetailsViewModel.let {
            it.isLoading.observe(this, Observer<Boolean> { showIndicator(it) })
            it.networkError.observe(this, Observer<String>{ error -> toast(error)})
            it.serverResponseError.observe(this, Observer<String>{ error -> toast(error)})
            profileDetailsViewModel.profileLiveData.observe(this, Observer<Profile>{

            })
        }
    }

    private fun initField() {
        setSupportActionBar(toolbar)
        with(supportActionBar!!) {
            title = "User Information"
            setDisplayHomeAsUpEnabled(true)
        }
    }

}
