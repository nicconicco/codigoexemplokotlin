package com.nico.projetopadroesnico.Common.View

import android.content.Context
import android.util.AttributeSet
import android.widget.ScrollView



/**
 * Created by 653835 on 23/10/2017.
 */

class ScrollViewExt : ScrollView {

    interface ScrollViewListener {
        fun onScrollChanged(scrollView: ScrollViewExt,
                            x: Int, y: Int, oldx: Int, oldy: Int)
    }

    private var scrollViewListener: ScrollViewListener? = null

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    fun setScrollViewListener(scrollViewListener: ScrollViewListener) {
        this.scrollViewListener = scrollViewListener
    }

    override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
        super.onScrollChanged(l, t, oldl, oldt)
        if (scrollViewListener != null) {
            scrollViewListener!!.onScrollChanged(this, l, t, oldl, oldt)
        }
    }
}