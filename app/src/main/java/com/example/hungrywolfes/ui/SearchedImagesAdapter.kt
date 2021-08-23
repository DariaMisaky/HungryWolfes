package com.example.hungrywolfes.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hungrywolfes.R
import com.example.hungrywolfes.network.ListMealsImages

class SearchedImagesAdapter(private val clickListener: (item: String) -> Unit) : RecyclerView.Adapter<SearchedImagesAdapter.ImageViewHolder>() {

    private val listDataImages: MutableList<ListMealsImages> = mutableListOf()
    class ImageViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        var mealsImages = view.findViewById<ImageView>(R.id.meal_image)
        val mealDescription = view.findViewById<TextView>(R.id.meal_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_searched_photos, parent, false)
        return ImageViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val photo = listDataImages[position]
        Glide.with(holder.mealsImages)
            .load(photo.strMealThumb)
            .placeholder(R.drawable.loading_img)
            .error(R.drawable.ic_connection_error)
            .into(holder.mealsImages)
        holder.itemView.setOnClickListener {
           clickListener(photo.idMeal)
        }
        holder.mealDescription.text =  photo.strMeal

    }
    fun setDataImages(dataImages: List<ListMealsImages>) {
        this.listDataImages.clear()
        this.listDataImages.addAll(dataImages)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return listDataImages.size
    }
}