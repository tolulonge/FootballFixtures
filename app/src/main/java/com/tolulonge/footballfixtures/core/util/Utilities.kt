package com.tolulonge.footballfixtures.core.util

import android.graphics.Color
import android.view.View
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.tolulonge.footballfixtures.R

fun View.showSnackBar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).setTextColor(ContextCompat.getColor(this.context, R.color.white))
        .setBackgroundTint(ContextCompat.getColor(this.context, R.color.secondaryColor))
        .show()
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.showSnackBarWithAction(message: String, actionText: String, action: (() -> Unit)) {
    Snackbar.make(this, message, Snackbar.LENGTH_INDEFINITE).setTextColor(ContextCompat.getColor(this.context, R.color.white))
        .setBackgroundTint(ContextCompat.getColor(this.context, R.color.primaryColor))
        .setAction(actionText) {
            action.invoke()
        }.setActionTextColor(Color.YELLOW)
        .show()
}