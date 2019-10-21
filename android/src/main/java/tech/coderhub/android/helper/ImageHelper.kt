package tech.coderhub.android.helper

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Base64

import java.io.ByteArrayOutputStream

private val ICON = 0
private val IMAGE = 100

fun stringToBitmap(image: String): Bitmap {
    val decodedByteArray = Base64.decode(image, Base64.NO_WRAP)
    return BitmapFactory.decodeByteArray(decodedByteArray, 0, decodedByteArray.size)
}

fun bitmapToStringImage(bitmap: Bitmap, quality: Int = IMAGE): String {
    val baosImage = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baosImage)
    return Base64.encodeToString(baosImage.toByteArray(), Base64.NO_WRAP)
}

fun bitmapToStringImageWEBP(bitmap: Bitmap, quality: Int = IMAGE): String {
    val baosImage = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.WEBP, quality, baosImage)
    return Base64.encodeToString(baosImage.toByteArray(), Base64.NO_WRAP)
}

fun byteArrayToStringImage(byteArray: ByteArray): String {
    return Base64.encodeToString(byteArray, Base64.NO_WRAP)
}

fun byteArrayOutputStreamToStringImage(byteArrayOutputStream: ByteArrayOutputStream): String {
    val byteArray: ByteArray = byteArrayOutputStream.toByteArray()
    return Base64.encodeToString(byteArray, Base64.NO_WRAP)
}

fun bitmapToStringIcon(bitmap: Bitmap): String {
    val baosImage = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.WEBP, ICON, baosImage)
    return Base64.encodeToString(baosImage.toByteArray(), Base64.NO_WRAP)
}

fun imageViewToOneMbBitmap(drawable: Drawable): Bitmap {
    val bitmap = (drawable as BitmapDrawable).bitmap
    var quality = 100
    while (quality>=1) {
        val string = bitmapToStringImage(bitmap, quality)
        println("$quality, ${string.length}")
        if (string.length <= 1.MB() || quality==1) {
            return stringToBitmap(string)
        }
        quality /= 2
    }
    return bitmap
}

fun Int.MB() = this * 1024 * 1024

