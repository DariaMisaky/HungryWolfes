package com.example.hungrywolfes.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hungrywolfes.R
import com.example.hungrywolfes.network.ListMealsImages


class FavoriteAdapter(
    private val onRemove: (ListMealsImages) -> Unit,
    private val clickListener: (item: String) -> Unit
) : RecyclerView.Adapter<FavoriteAdapter.ImageViewHolder>() {

    private var listMealsImages: MutableList<ListMealsImages> = mutableListOf()

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
        val photo = listMealsImages[position]

        Glide.with(holder.mealsImages)
            .load(photo.strMealThumb)
            .placeholder(R.drawable.loading_img)
            .error(R.drawable.ic_connection_error)
            .into(holder.mealsImages)
        holder.mealName.text = photo.strMeal
        holder.itemView.setOnLongClickListener {
            listMealsImages.remove(photo)
            onRemove(photo)
            notifyDataSetChanged()
            true
        }
        holder.itemView.setOnClickListener {
            clickListener(photo.idMeal)
        }
    }

    fun setDataImages(dataImages: MutableList<ListMealsImages>) {
        this.listMealsImages.clear()
        this.listMealsImages.addAll(dataImages)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return listMealsImages.size
    }
}