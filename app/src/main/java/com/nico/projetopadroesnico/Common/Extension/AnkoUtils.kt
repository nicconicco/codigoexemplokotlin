package com.nico.projetopadroesnico.Common.Extension

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri

@SuppressLint("MissingPermission")
// Took it from original Anko documentation
// Make a call

//makeCall(number)
fun Context.makeCall(number: String): Boolean {
    try {
        val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:$number"))
        startActivity(intent)
        return true
    } catch (e: Exception) {
        e.printStackTrace()
        return false
    }
}

// Send a text
//sendSMS(number, [text])

//Browse the web
//browse(url)

//Share some text
//share(text, [subject])

//Send a email
//email(email, [subject], [text])
//Arguments in square brackets ([]) are optional