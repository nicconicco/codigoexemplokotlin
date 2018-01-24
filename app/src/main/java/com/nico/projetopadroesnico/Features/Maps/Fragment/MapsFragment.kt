package com.nico.projetopadroesnico.Features.Maps.Fragment

import android.Manifest
import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.portoseguro.portoseguroconsorcio.common.Utils.PermissionUtils
import com.nico.projetopadroesnico.Common.Fragment.BaseFragment
import com.nico.projetopadroesnico.Features.Maps.Presenter.MapsPresenter
import com.nico.projetopadroesnico.R
import kotlinx.android.synthetic.main.fragment_maps.*

class MapsFragment : BaseFragment() {

    lateinit var presenter: MapsPresenter
    private var isVisibleToUser = false
    private val TAG = MapsFragment::class.java.simpleName

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, icicle: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = MapsPresenter()

        googleMapsApi.setOnClickListener {
            context?.let {
                presenter.openGoogleMaps(it)
            }
        }

        wazeApi.setOnClickListener {
            context?.let {
                presenter.openWaze(it)
            }
        }

        uberApi.setOnClickListener {
            context?.let {
                presenter.openUber(it)
            }
        }

        if (isVisibleToUser) {
            showMapWithPermissionRights()
        }
    }

    override fun onResume() {
        super.onResume()
        if (isVisibleToUser) {
            showMapWithPermissionRights()
        }
    }

    private fun showMapWithPermissionRights() {
        context?.let {
            if (PermissionUtils.isValidGPSPermission(it)) {
                showMapsInActivity()
            } else {
                PermissionUtils.validate(activity!!, 0,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION)
            }
        }
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        this.isVisibleToUser = isVisibleToUser
        if (isVisibleToUser) {
            showMapsInActivity()
        }
    }

    private fun showMapsInActivity() {
        activity?.let {
            val activity = it
            context?.let {
                val context = it
                fragmentManager?.let {
                    presenter.showMap(activity, context, childFragmentManager, it)
                }
            }
        }
    }

    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>,
                                            grantResults: IntArray) {
        Log.i(TAG, "onRequestPermissionResult")
        if (requestCode == REQUEST_PERMISSIONS_REQUEST_CODE) {
            when {
                grantResults.isEmpty() -> {
                    Log.i(TAG, "User interaction was cancelled.")
                }
                grantResults[0] == PackageManager.PERMISSION_GRANTED -> {
                    showMapsInActivity()
                    Log.i(TAG, "Permission granted, updates requested, starting location updates")
                }
            }
        }
    }
}