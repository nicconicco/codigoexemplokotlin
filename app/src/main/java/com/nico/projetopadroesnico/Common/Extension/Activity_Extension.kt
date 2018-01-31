package com.cognizant.dor.Common.Extensions

import android.app.Activity
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.content.pm.ApplicationInfo
import com.nico.projetopadroesnico.Application.NicoApplication


/**
 * Created by nicolaugalves on 05/09/17.
 */
val Activity.app: NicoApplication
    get() = application as NicoApplication

// findViewById + setOnClickListener
fun AppCompatActivity.onClick(@IdRes viewId: Int, onClick: (v: android.view.View?) -> Unit) {
    val view = findViewById<View>(viewId)
    view.setOnClickListener { onClick(it) }
}

// Mostra um toast
// Lib Anko foi adicionada ao projeto
/*fun Activity.toast(message: CharSequence, length: Int = Toast.LENGTH_SHORT) =
        Toast.makeText(this, message, length).show()

fun Activity.toast(@StringRes message: Int, length: Int = Toast.LENGTH_SHORT) =
        Toast.makeText(this, message, length).show()*/

// Configura a Toolbar
fun AppCompatActivity.setupToolbar(@IdRes id: Int, title: String? = null, upNavigation: Boolean = false): ActionBar {
    val toolbar = findViewById<Toolbar>(id)
    setSupportActionBar(toolbar)
    if (title != null) {
        supportActionBar?.title = title
    }
    supportActionBar?.setDisplayHomeAsUpEnabled(upNavigation)
    return supportActionBar!!
}

fun Activity.isDebuggable () : Boolean {
    return 0 != applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE;
}

// Adiciona o fragment no layout
fun AppCompatActivity.addFragment(@IdRes layoutId: Int, fragment: Fragment) {
    fragment.arguments = intent.extras
    val ft = supportFragmentManager.beginTransaction()
    ft.add(layoutId, fragment)
    ft.commit()
}