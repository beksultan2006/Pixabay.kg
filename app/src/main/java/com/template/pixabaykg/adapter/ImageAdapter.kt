package com.template.pixabaykg.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.template.pixabaykg.databinding.ItemImageBinding
import com.template.pixabaykg.model.ImageModel

class ImageViewHolder(private val binding: ItemImageBinding) : ViewHolder(binding.root) {

    fun onBind(image: ImageModel) {
        binding.imageView.load(image.largeImageURL)
    }
}

class ImageAdapter(var list: ArrayList<ImageModel>) : RecyclerView.Adapter<ImageViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder =
        ImageViewHolder(
            ItemImageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.onBind(list[position])
    }
}