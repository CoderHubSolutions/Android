package tech.coderhub.android.base

import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.android.synthetic.main.app_bar_layout.*
import kotlinx.android.synthetic.main.progressbar.*
import org.jetbrains.anko.toast
import tech.coderhub.android.R


abstract class BaseListActivity : BaseActivity() {
    lateinit var baseViewModel: BaseViewModel

    override fun layoutRes() = R.layout.activity_list
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        baseViewModel = ViewModelProviders.of(this, viewModelFactory).get(BaseViewModel::class.java)
        progressBar.bringToFront()
        setSupportActionBar(toolbar)
        with(supportActionBar!!) {
            setDisplayHomeAsUpEnabled(true)
        }
        list_recyclerView.let {
            it.layoutManager = LinearLayoutManager(this)
            it.setHasFixedSize(true)
        }
        baseViewModel.let {
            it.isLoading.observe(this, Observer<Boolean> { showIndicator(it) })
            it.networkError.observe(this, Observer<String>{ error -> toast(error)})
            it.serverResponseError.observe(this, Observer<String>{ error -> toast(error)})
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
