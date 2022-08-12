package com.dungnd.mvvm.ui.chuabuoi11

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dungnd.mvvm.data.remote.model.Product
import com.dungnd.mvvm.data.remote.model.Photo
import com.dungnd.mvvm.databinding.ItemPhotoBinding
import com.dungnd.mvvm.util.setOnSingClickListener

class PhotoListAdapter : RecyclerView.Adapter<PhotoListAdapter.PhotoVH>() {
    inner class PhotoVH constructor(private val binding: ItemPhotoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product?) {
            binding.productResponse = product
        }
    }

    var photoList: List<Product>? = null
    var onPhotoClick: ((Product?) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoVH {
        val binding = ItemPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhotoVH(binding)
    }

    override fun onBindViewHolder(holder: PhotoVH, position: Int) {
        holder.bind(photoList?.get(position))
        holder.itemView.setOnSingClickListener {
            onPhotoClick?.invoke(photoList?.get(position))
        }
    }

    override fun getItemCount(): Int = photoList?.size ?: 0
}