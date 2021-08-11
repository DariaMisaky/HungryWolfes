package com.example.hungrywolfes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView

class ImagesAdapter :RecyclerView.Adapter<ImagesAdapter.ImageViewHolder>(){
    private val list = ('D').rangeTo('Z').toList()
    class ImageViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val button = view.findViewById<Button>(R.id.button_item)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_view, parent, false)

        return ImageViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val item = list.get(position)
        holder.button.text = item.toString()

        // Assigns a [OnClickListener] to the button contained in the [ViewHolder]
        holder.button.setOnClickListener {
            holder.view.findNavController().navigate(R.id.action_homeScreen_to_fragment_search_screen)

        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}