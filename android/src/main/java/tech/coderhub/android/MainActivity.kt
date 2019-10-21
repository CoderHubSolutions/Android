package tech.coderhub.android

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import tech.coderhub.android.ui.loginScreen.LoginActivity
import tech.coderhub.android.ui.profileDetailsScreen.ProfileDetailsActivity
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_layout.*
import org.jetbrains.anko.clearTop
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivity
import tech.coderhub.android.base.BaseActivity
import tech.coderhub.android.helper.Constants

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {
    override fun layoutRes() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()
        navigationView.setNavigationItemSelectedListener(this)
        setUpRoleBaseView()
    }

    private fun setUpRoleBaseView() {
//        navigationView.menu.findItem(R.id.nav_order).isVisible = true
    }

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) drawer.closeDrawer(GravityCompat.START)
        else super.onBackPressed()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_navigaion, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) =
            if (item.itemId == R.id.action_settings) true
            else super.onOptionsItemSelected(item)

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> { }
            R.id.nav_profile -> startActivity<ProfileDetailsActivity>()
            R.id.nav_logout -> {
                cache!!.putValue(Constants.ACCESS_TOKEN, "")
                startActivity(intentFor<LoginActivity>().clearTop())
                finish()
            }
        }
        drawer.closeDrawer(GravityCompat.START)
        return true
    }
}
