package com.tolulonge.footballfixtures.core.util

import android.graphics.Color
import android.media.Image
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.snackbar.Snackbar
import com.tolulonge.footballfixtures.R
import com.tolulonge.footballfixtures.presentation.state.MatchStatus

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

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.showSnackBarWithAction(message: String, actionText: String, action: (() -> Unit)) {
    Snackbar.make(this, message, Snackbar.LENGTH_INDEFINITE).setTextColor(ContextCompat.getColor(this.context, R.color.white))
        .setBackgroundTint(ContextCompat.getColor(this.context, R.color.primaryColor))
        .setAction(actionText) {
            action.invoke()
        }.setActionTextColor(Color.YELLOW)
        .show()
}

fun ImageView.loadSvgOrOther(myUrl: String?, cache: Boolean = true,
                             errorImg: Int = R.drawable.icons_default_flag_60,
                             placeHolderImg:Int = R.drawable.loading_status_animation ){// Place any error image from your drawable) {

myUrl?.let {
    if (it.lowercase().endsWith("svg")) {
        val imageLoader = ImageLoader.Builder(this.context)
            .componentRegistry {
                add(SvgDecoder(this@loadSvgOrOther.context))
            }.build()

        val request = ImageRequest.Builder(this.context).apply {
            error(errorImg)
            placeholder(placeHolderImg)
            data(it).decoder(SvgDecoder(this@loadSvgOrOther.context))
        }.target(this).build()

        imageLoader.enqueue(request)
    } else {
        val imageLoader = ImageLoader(context)

        val request = ImageRequest.Builder(context).apply {
            if (cache) {
                memoryCachePolicy(CachePolicy.ENABLED)
            } else {
                memoryCachePolicy(CachePolicy.DISABLED)
            }
            error(errorImg)
            placeholder(errorImg)
            data(it)
        }.target(this).build()

        imageLoader.enqueue(request)
    }
}
}

fun ImageView.loadDefaultFlag(){
    this.setImageResource(R.drawable.icons_default_flag_60)
}
