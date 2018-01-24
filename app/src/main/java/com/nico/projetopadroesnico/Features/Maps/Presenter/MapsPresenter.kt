package com.nico.projetopadroesnico.Features.Maps.Presenter

import android.Manifest
import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.support.v4.content.ContextCompat.startActivity
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import br.com.portoseguro.portoseguroconsorcio.common.Utils.PermissionUtils
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.nico.projetopadroesnico.R


class MapsPresenter {

    fun showMap(activity: FragmentActivity, context: Context,  childFragmentManager: FragmentManager, fragmentManager: FragmentManager) {

            if (PermissionUtils.isValidGPSPermission(context)) {
                var mSupportMapFragment = childFragmentManager.findFragmentById((R.id.mSupportMapFragment)) as SupportMapFragment?

                if (mSupportMapFragment == null) {
                    val fragmentTransaction = fragmentManager.beginTransaction()
                    mSupportMapFragment = SupportMapFragment.newInstance()
                    fragmentTransaction.replace(R.id.mSupportMapFragment, mSupportMapFragment).commit()
                }

                mSupportMapFragment?.getMapAsync(OnMapReadyCallback { googleMap ->
                    if (googleMap != null) {
                        googleMap.uiSettings.setAllGesturesEnabled(true)
                        setMapLocation(activity,context, googleMap, mSupportMapFragment)
                    }
                })
            } else {
                PermissionUtils.validate(activity!!, 0,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION)
            }
    }

    @SuppressLint("MissingPermission", "ResourceType")
    fun setMapLocation(activity: FragmentActivity, context: Context, googleMap: GoogleMap, mSupportMapFragment: SupportMapFragment?) {

        googleMap.clear()
        val location = getLocationFake()
        val update = CameraUpdateFactory.newLatLngZoom(location, 15f)
        googleMap.animateCamera(update)

        PermissionUtils.validate(activity, 0,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION)

        if (PermissionUtils.isValidGPSPermission(context)) {
            googleMap.uiSettings!!.isMyLocationButtonEnabled = true
            googleMap.isMyLocationEnabled = true
        }

        googleMap.addMarker(MarkerOptions().position(location).icon(BitmapDescriptorFactory.fromResource(R.drawable.pin_mapa)).title("Maps nico"))
        // Custom button
        mSupportMapFragment?.let {
            customButtonMap(mSupportMapFragment)
        }
    }

    private fun getLocationFake(): LatLng {
        val lat = "-23.622584".toDouble()
        val long = "-46.698848".toDouble()
        val location = LatLng(lat, long)
        return location
    }

    @SuppressLint("ResourceType")
    private fun customButtonMap(mSupportMapFragment: SupportMapFragment) {

        val mapView = mSupportMapFragment.view

        if (mapView?.findViewById<View>(1) != null) {
            val locationButton2 = mapView.findViewById<ImageView>(2) as ImageView
            locationButton2.setImageResource(R.drawable.icone_centralizar_mapa)
            // Get the button view
            val locationButton = (mapView.findViewById<View>(1).parent as View).findViewById<View>(2)
            val layoutParams = locationButton.layoutParams as RelativeLayout.LayoutParams
            // position on right bottom
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP, 0)
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE)
            layoutParams.setMargins(0, 0, 16, 9)
        }
    }

    fun openWaze(context: Context) {
        try {
            val location = getLocationFake()
            val uri = String.format("https://waze.com/ul?ll=${location.latitude},${location.longitude}&navigate=yes")
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
            context.startActivity(intent)
        } catch (ex: ActivityNotFoundException) {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.waze"))
            context.startActivity(intent)
        }
    }

    fun openGoogleMaps(context: Context) {
        try {
            val location = getLocationFake()
            val uri = "http://maps.google.com/maps?saddr=${location.latitude},${location.longitude}&daddr=${location.latitude},${location.longitude}"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
            context.startActivity(intent)

        } catch (ex: ActivityNotFoundException) {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps"))
            context.startActivity(intent)
        }
    }

    fun openUber(context: Context) {
        try {
            val location = getLocationFake()
            val string = "action=setPickup&pickup=my_location&dropoff[latitude]=${location.latitude}&dropoff[longitude]=${location.longitude}&dropoff[nickname]=${"FakeNameToPassToApp"}"
            val uri = String.format("uber://?" + string)
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
            context.startActivity(intent)
        } catch (ex: ActivityNotFoundException) {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.ubercab"))
            context.startActivity(intent)
        }
    }
}