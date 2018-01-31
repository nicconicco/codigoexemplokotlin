package com.nico.projetopadroesnico.Common.Extension

import android.app.Activity
import android.app.AlertDialog
import android.os.Build
import android.support.annotation.StringRes
import android.support.v4.app.FragmentActivity
import android.text.Html
import android.text.Spanned


fun @receiver:StringRes Int.errorDialog(activity: Activity) {
    AlertDialog.Builder(activity, android.R.style.Theme_Material_Dialog_Alert)
            .setTitle("Error")
            .setMessage(this@errorDialog)
            .setPositiveButton(android.R.string.ok) { dialog, _ -> dialog.dismiss() }
            .setIcon(android.R.drawable.ic_dialog_alert).show()
}

fun @receiver:StringRes Int.errorDialog(activity: FragmentActivity) {
    AlertDialog.Builder(activity, android.R.style.Theme_Material_Dialog_Alert)
            .setTitle("Error")
            .setMessage(this@errorDialog)
            .setPositiveButton(android.R.string.ok) { dialog, _ -> dialog.dismiss() }
            .setIcon(android.R.drawable.ic_dialog_alert).show()
}

fun String?.parseHtml(): Spanned {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        return Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY)
    } else {
        @Suppress("deprecation")
        return Html.fromHtml(this)
    }
}