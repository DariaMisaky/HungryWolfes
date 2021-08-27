package com.example.hungrywolfes.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.hungrywolfes.R


private const val TAG = "DetailsAdapter"
class DetailsAdapter: RecyclerView.Adapter<DetailsAdapter.ImageViewHolder>() {

    private val listDataTags: MutableList<String> = mutableListOf()

    class ImageViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        var tagButton: Button = view.findViewById(R.id.button_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_tag, parent, false)
        return ImageViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
      holder.tagButton.text=listDataTags[position]
    }
    fun setDataTags(dataTags: List<String>) {
        this.listDataTags.clear()
        this.listDataTags.addAll(dataTags)
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int {
        return listDataTags.size
    }
}