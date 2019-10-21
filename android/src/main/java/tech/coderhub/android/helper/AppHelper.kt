package tech.coderhub.android.helper

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity

import java.io.ByteArrayOutputStream
import java.net.URL


fun getFileDataFromDrawable(drawable: Drawable): ByteArray {
    return try {
        val bitmap = (drawable as BitmapDrawable).bitmap
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, byteArrayOutputStream)
        byteArrayOutputStream.toByteArray()
    } catch (e: Exception) {
        val byteArrayOutputStream = ByteArrayOutputStream()
        byteArrayOutputStream.toByteArray()
    }
}
fun AppCompatActivity.getDeviceId(): String {
    var deviceId = Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
    if (deviceId == null) deviceId = "not found"
    return deviceId
}

fun getIpFromAmazon()= URL("http://checkip.amazonaws.com/").readText()