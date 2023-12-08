package com.example.openweatherapp.ui.widget

import android.content.Context
import androidx.swiperefreshlayout.widget.CircularProgressDrawable

object CircularProgress {

    fun get(context: Context): CircularProgressDrawable {
        val circularProgressDrawable = CircularProgressDrawable(context)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()

        return circularProgressDrawable
    }
}