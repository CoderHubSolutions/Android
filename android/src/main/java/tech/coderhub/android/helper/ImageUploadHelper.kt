package tech.coderhub.android.helper

import android.Manifest
import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.layout_image_pick.*
import tech.coderhub.android.R

@SuppressLint("WrongConstant")
fun AppCompatActivity.uploadImageToView() {
    with(Dialog(this)) {
        setContentView(R.layout.layout_image_pick)
        setCancelable(true)
        show()
        cancel.setOnClickListener { dismiss() }
        camera.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    startCamera()
                    dismiss()
                } else {
                    requestPermissions(arrayOf(Manifest.permission.CAMERA), Constants.CAMERA_PERMISSION_CODE)
                }
            else {
                startCamera()
                dismiss()
            }
        }
        gallery.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    startGallery()
                    dismiss()
                } else {
                    requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), Constants.STORAGE_PERMISSIOIN_CODE)
                }
            else {
                startGallery()
                dismiss()
            }
        }
    }
}

fun AppCompatActivity.startCamera() {
    val takePicture = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
    startActivityForResult(takePicture, Constants.TAKE_PICTURE_CODE)
}

fun AppCompatActivity.startGallery() {
    val pickPhoto = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
    startActivityForResult(pickPhoto, Constants.PICK_PHOTO_CODE)
}
