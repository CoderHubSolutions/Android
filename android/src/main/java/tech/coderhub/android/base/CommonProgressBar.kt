package tech.coderhub.android.base

import android.view.View
import android.view.WindowManager
import kotlinx.android.synthetic.main.progressbar.*

//control progress indicator
fun BaseActivity.showIndicator(isLoading: Boolean) {
    if (isLoading) {
        progressBar.visibility = View.VISIBLE
        window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    } else {
        progressBar.visibility = View.GONE
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }

}
