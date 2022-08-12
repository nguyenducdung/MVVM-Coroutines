package com.dungnd.mvvm.ui.base

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dungnd.mvvm.R

object BindingView {

    @JvmStatic
    @BindingAdapter(value = ["image_url"], requireAll = false)
    fun imageUrl(imageView: ImageView, imageUrl: String?) {
        Glide.with(imageView.context)
            .load(imageUrl)
            .apply(
                RequestOptions()
                    .error(R.drawable.ic_launcher_background)
            )
            .circleCrop()
            .into(imageView)
    }
}