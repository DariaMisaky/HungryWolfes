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

private const val TAG = "FavoriteAdapter"

class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.ImageViewHolder>() {

    private var ListMealsImages: MutableList<ListMealsImages> = mutableListOf()

    class ImageViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        var mealsImages: ImageView = view.findViewById(R.id.meal_image)
        var mealName: TextView = view.findViewById(R.id.meal_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_favorites, parent, false)
        return ImageViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val photo = ListMealsImages[position]
        val image=photo.strMealThumb
        val idMeal=photo.idMeal
        val nameMeal=photo.strMeal

        Glide.with(holder.mealsImages)
            .load(image)
            .placeholder(R.drawable.loading_img)
            .error(R.drawable.ic_connection_error)
            .into(holder.mealsImages)
        holder.mealName.text = nameMeal
        holder.itemView.setOnLongClickListener {
            ListMealsImages.remove(ListMealsImages[position])
            notifyDataSetChanged()
            true
        }
    }

    fun setDataImages(dataImages: MutableList<ListMealsImages>) {
        this.ListMealsImages.clear()
        this.ListMealsImages.addAll(dataImages)
        notifyDataSetChanged()
        Log.d(TAG, "setDataImages: $ListMealsImages")
    }

    override fun getItemCount(): Int {
        return ListMealsImages.size
    }
}