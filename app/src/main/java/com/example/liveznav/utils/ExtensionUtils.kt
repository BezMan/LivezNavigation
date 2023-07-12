package com.example.liveznav.utils

import android.view.View

class ExtensionUtils {

    companion object {

        fun View.toggleShowView(show: Boolean) {
            visibility = if (show) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }

    }
}