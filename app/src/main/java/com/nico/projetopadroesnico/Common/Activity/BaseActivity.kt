@file:Suppress("DEPRECATION")

package com.nico.projetopadroesnico.Common.Activity

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView


open class BaseActivity: AppCompatActivity() {

    protected val context: Context get() = this

    fun showDialog(dialog: ProgressDialog) {
        dialog.show()
    }

    fun cancelDialog(dialog: ProgressDialog) {
        dialog.dismiss()
    }

    fun showSnack(view: Activity) {
        val parentLayout = view.findViewById<View>(android.R.id.content)
        val yourText = "A comunicação com o servidor não está disponível no momento.\nPor favor, tente mais tarde."
        val snackbar = Snackbar.make(parentLayout, yourText, Snackbar.LENGTH_SHORT)
        val textView = snackbar.view.findViewById<View>(android.support.design.R.id.snackbar_text) as TextView
        textView.setSingleLine(false)
        snackbar.setAction("Ok") {

        }
        snackbar.show()
    }
}