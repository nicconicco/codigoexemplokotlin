package com.nico.projetopadroesnico.Common.View

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.TextView

/**
 * Created by nicolaugalves on 09/09/17.
 */


class RobotoTextViewMedium : TextView {

    constructor(context: Context) : super(context) {
        init(null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        if (!isInEditMode) {
            if (attrs == null) {
                val tf = Typeface.createFromAsset(context.assets, "fonts/Roboto-Medium.ttf")
                typeface = tf
            } else {
                val textStyle = attrs.getAttributeValue("http://schemas.android.com/apk/res/android", "textStyle")
                val tf: Typeface
                if (textStyle == null) {
                    tf = Typeface.createFromAsset(context.assets, "fonts/Roboto-Medium.ttf")
                    typeface = tf
                } else {
                    when (textStyle) {
                        "0x" + Typeface.BOLD -> {
                            tf = Typeface.createFromAsset(context.assets, "fonts/Roboto-Bold.ttf")
                            typeface = tf
                        }
                        "0x" + Typeface.BOLD_ITALIC -> {
                            tf = Typeface.createFromAsset(context.assets, "fonts/Roboto-BoldItalic.ttf")
                            typeface = tf
                        }
                        "0x" + Typeface.NORMAL -> {
                            tf = Typeface.createFromAsset(context.assets, "fonts/Roboto-Light.ttf")
                            typeface = tf
                        }
                    }
                }

            }
        }
    }

}