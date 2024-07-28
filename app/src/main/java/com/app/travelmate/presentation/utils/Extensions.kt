package com.app.travelmate.presentation.utils

import android.view.View

object Extensions {

    fun View.visibilityToggle(display: Boolean) {
        if (display) {
            this.visibility = View.VISIBLE
        } else {
            this.visibility = View.GONE
        }
    }

}