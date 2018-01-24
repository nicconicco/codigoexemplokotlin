package br.com.portoseguro.portoseguroconsorcio.common.Utils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.support.v4.app.ActivityCompat
import android.support.v4.app.FragmentActivity
import android.support.v4.content.ContextCompat

/**
 * Created by nicolaugalves on 07/08/17.
 */


object PermissionUtils {

    const val REQUEST_CODE_TELFONE = 1
    const val REQUEST_CODE_CAMERA = 0
    const val REQUEST_LOAD_IMAGE= 3
    const val PERMISSIONS_REQUEST_CODE= 4

    // Validate permission
    fun validate(activity: FragmentActivity, requestCode: Int, vararg permissions: String): Boolean {
        val list = ArrayList<String>()
        for (permission in permissions) {
            // Valida permissão
            val ok = ContextCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_GRANTED
            if (!ok) {
                list.add(permission)
            }
        }
        if (list.isEmpty()) {
            // Tudo ok, retorna true
            return true
        }
        // Lista de permissões que falta acesso.
        val newPermissions = arrayOfNulls<String>(list.size)
        list.toArray(newPermissions)
        // Solicita permissão
        ActivityCompat.requestPermissions(activity, newPermissions, 1)
        return false
    }

    fun validate(activity: Activity, requestCode: Int, vararg permissions: String): Boolean {
        val list = ArrayList<String>()
        for (permission in permissions) {
            // Valida permissão
            val ok = ContextCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_GRANTED
            if (!ok) {
                list.add(permission)
            }
        }
        if (list.isEmpty()) {
            // Tudo ok, retorna true
            return true
        }
        // Lista de permissões que falta acesso.
        val newPermissions = arrayOfNulls<String>(list.size)
        list.toArray(newPermissions)
        // Solicita permissão
        ActivityCompat.requestPermissions(activity, newPermissions, 1)
        return false
    }

    fun isCallPermissionOk(context: Context): Boolean {
        return checkPermission(context, "android.permission.CALL_PHONE")
    }

    fun isCalendarPermission(context: Context): Boolean {
        val ok = checkPermission(context, "android.permission.READ_CALENDAR")
        val ok2 = checkPermission(context, "android.permission.WRITE_CALENDAR")
        return ok && ok2
    }

    fun checkPermission(context: Context, permission: String): Boolean {
        return ContextCompat.checkSelfPermission(context, permission) == 0
    }

    fun isPhotoPermission(context: Context): Boolean {
        return checkPermission(context, "android.permission.CAMERA")
    }

    fun isE(context: Context): Boolean {
        return checkPermission(context, "android.permission.CAMERA")
    }

    fun validatePermissionsOfCallPhone(activity: Activity) {
        val marshmallow = Build.VERSION.SDK_INT >= 22
        if (marshmallow) {
            val permissions = arrayOf(Manifest.permission.CALL_PHONE)
            PermissionUtils.validate(activity, REQUEST_CODE_TELFONE, *permissions)
        }
    }

    fun validatePermissionsOfCalendar(activity: Activity) {
        val marshmallow = Build.VERSION.SDK_INT >= 22
        if (marshmallow) {
            val permissions = arrayOf(Manifest.permission.READ_CALENDAR, Manifest.permission.WRITE_CALENDAR)
            PermissionUtils.validate(activity, 0, *permissions)
        }
    }


    fun validatePermissionsOfCamera(activity: Activity) {
        val marshmallow = Build.VERSION.SDK_INT >= 22
        if (marshmallow) {
            val permissions = arrayOf(Manifest.permission.CAMERA)
            PermissionUtils.validate(activity, REQUEST_CODE_CAMERA, *permissions)
        }
    }


    fun isWriteReadPermission(context: Context): Boolean {
        return checkPermission(context, "android.permission.WRITE_EXTERNAL_STORAGE") &&
                checkPermission(context, "android.permission.READ_EXTERNAL_STORAGE")

    }

    fun isReadPermission(context: Context): Boolean {
        return checkPermission(context, "android.permission.READ_EXTERNAL_STORAGE")

    }

    fun validatePermissionLoad(activity: Activity) {
        val marshmallow = Build.VERSION.SDK_INT >= 22
        if (marshmallow) {
            val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
            PermissionUtils.validate(activity, REQUEST_LOAD_IMAGE, *permissions)
        }
    }

    fun validatePermissionWriteLoad(activity: Activity) {
        val marshmallow = Build.VERSION.SDK_INT >= 22
        if (marshmallow) {
            val permissions = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            PermissionUtils.validate(activity, 0, *permissions)
        }
    }

    fun isValidGPSPermission(context: Context): Boolean {
        return checkPermission(context, "android.permission.ACCESS_COARSE_LOCATION")
        && checkPermission(context, "android.permission.ACCESS_FINE_LOCATION")
    }

    fun validatePermissionGPS(activity: Activity) {
        val marshmallow = Build.VERSION.SDK_INT >= 22
        if (marshmallow) {
            val permissions = arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION)
            PermissionUtils.validate(activity, 0, *permissions)
        }
    }

    fun requestLocationGPS(activity: Activity) {
        ActivityCompat.requestPermissions(activity,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                PERMISSIONS_REQUEST_CODE)
    }

}