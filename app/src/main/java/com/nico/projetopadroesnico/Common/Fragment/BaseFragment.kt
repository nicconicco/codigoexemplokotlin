package com.nico.projetopadroesnico.Common.Fragment

import android.app.Activity
import android.support.v4.app.FragmentActivity
import br.com.portoseguro.portoseguroconsorcio.common.Utils.PermissionUtils
import com.google.android.gms.location.*
import kotlinx.coroutines.experimental.Job
import java.text.DateFormat
import java.util.*


open class BaseFragment : android.support.v4.app.Fragment() {
    protected var job: Job? = null

    override fun onResume() {
        super.onResume()
        job = null
    }

    override fun onPause() {
        super.onPause()
        job?.cancel()
        job = null
    }

    // Maps

    // Location Config
    open val REQUEST_PERMISSIONS_REQUEST_CODE = 34
    protected open val REQUEST_CHECK_SETTINGS = 0x1
    private val UPDATE_INTERVAL_IN_MILLISECONDS: Long = 10000
    private val FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS = UPDATE_INTERVAL_IN_MILLISECONDS / 2

    private var mFusedLocationClient: FusedLocationProviderClient? = null
    private var mSettingsClient: SettingsClient? = null
    private var mLocationRequest: LocationRequest? = null
    private var mLocationSettingsRequest: LocationSettingsRequest? = null
    private var mLocationCallback: LocationCallback? = null
    protected open var mCurrentLocation: android.location.Location? = null
    protected open var makeRequest: Boolean = true

    protected open var mLastUpdateTime: String? = null

    protected open fun initLocation(activity: FragmentActivity) {
        mLastUpdateTime = ""

        PermissionUtils.requestLocationGPS(activity)

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(activity)
        mSettingsClient = LocationServices.getSettingsClient(activity)

        createLocationCallback()
        createLocationRequest()
        buildLocationSettingsRequest()
    }

    private fun createLocationCallback() {
        mLocationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                super.onLocationResult(locationResult)

                mCurrentLocation = locationResult!!.lastLocation
                mLastUpdateTime = DateFormat.getTimeInstance().format(Date())
                updateLocationUI()
            }
        }
    }

    private fun createLocationRequest() {
        mLocationRequest = LocationRequest()
        mLocationRequest?.interval = UPDATE_INTERVAL_IN_MILLISECONDS
        mLocationRequest?.fastestInterval = FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS
        mLocationRequest?.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
    }

    private fun buildLocationSettingsRequest() {
        val builder = LocationSettingsRequest.Builder()
        mLocationRequest?.let {
            builder.addLocationRequest(it)
            mLocationSettingsRequest = builder.build()
        }
    }

    // Override updateLocationUI this methods if u want location
    protected open fun updateLocationUI() {
    }

}