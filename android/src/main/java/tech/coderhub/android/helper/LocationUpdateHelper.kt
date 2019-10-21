package tech.coderhub.android.helper

import android.Manifest
import android.annotation.SuppressLint
import android.content.IntentSender
import android.content.pm.PackageManager
import android.location.Location
import android.os.Looper
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.tasks.Task
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationCallback


fun AppCompatActivity.checkPermissions(vararg perm: String): Boolean {
    val havePermissions = perm.toList().all {
        ContextCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_GRANTED
    }
    if (!havePermissions) {
        ActivityCompat.requestPermissions(this, perm, Constants.LOCATION_PERMISSION_CODE)
        return false
    }
    return true
}


fun getLocationRequest(): LocationRequest =
        LocationRequest.create().apply {
            fastestInterval = 1000
            interval = 1000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            smallestDisplacement = 1.0f
        }

fun getLocationCallback(lat: EditText, lon: EditText): LocationCallback {
    return object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult?) {
            locationResult ?: return
            for (location in locationResult.locations) {
                updateLocationUI(lat, lon, location)
            }
        }
    }
}

fun AppCompatActivity.initLocation(lat: EditText, lon: EditText): Triple<FusedLocationProviderClient, LocationRequest, LocationCallback> {
    var fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
    val locationRequest = getLocationRequest()
    val locationCallback = getLocationCallback(lat, lon)
    fusedLocationClient = startLocationUpdates(fusedLocationClient, locationRequest, locationCallback, lat, lon)
    return Triple(fusedLocationClient, locationRequest, locationCallback)
}

@SuppressLint("SetTextI18n")
fun updateLocationUI(lat: EditText, lon: EditText, location: Location?) {
    lat.setText("" + location?.latitude)
    lon.setText("" + location?.longitude)

    lat.alpha = 0F
    lat.animate().alpha(1F).duration = 300

    lon.alpha = 0F
    lon.animate().alpha(1F).duration = 300
}


fun AppCompatActivity.startLocationUpdates(fusedLocationClient: FusedLocationProviderClient, locationRequest: LocationRequest, locationCallback: LocationCallback, lat: EditText, lon: EditText): FusedLocationProviderClient {
    val builder = LocationSettingsRequest.Builder().addLocationRequest(locationRequest)
    val client: SettingsClient = LocationServices.getSettingsClient(this)
    val task: Task<LocationSettingsResponse> = client.checkLocationSettings(builder.build())
    task.addOnSuccessListener {
        if (checkPermissions(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION)) {
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                updateLocationUI(lat, lon, location)
            }
            fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper())
        }
    }
    task.addOnFailureListener { exception ->
        if (exception is ResolvableApiException) {
            try {
                exception.startResolutionForResult(this, Constants.LOCATION_CHECK_SETTINGS_CODE)
            } catch (sendEx: IntentSender.SendIntentException) {
                // Ignore the error.
            }
        }
    }
    return fusedLocationClient
}

fun stopLocationUpdates(fusedLocationClient: FusedLocationProviderClient, locationCallback: LocationCallback): FusedLocationProviderClient {
    fusedLocationClient.removeLocationUpdates(locationCallback)
    return fusedLocationClient
}