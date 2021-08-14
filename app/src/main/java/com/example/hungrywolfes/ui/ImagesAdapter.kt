package com.example.hungrywolfes.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hungrywolfes.R
import com.example.hungrywolfes.network.ListMealsImages

class ImagesAdapter : RecyclerView.Adapter<ImagesAdapter.ImageViewHolder>() {

    private val listDataImages: MutableList<ListMealsImages> = mutableListOf()

    class ImageViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        var mealsImages = view.findViewById<ImageView>(R.id.meal_image)
        val mealDescription = view.findViewById<TextView>(R.id.meal_description)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_photos, parent, false)
        return ImageViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val photo = listDataImages[position]
        val imageDescription = photo.strMeal
        val imageUrl = photo.strMealThumb
        var words=imageDescription.split(" ")
        Log.d("dada","$words")
        Glide.with(holder.mealsImages).load(imageUrl).into(holder.mealsImages);
        holder.mealDescription.text = imageDescription



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


