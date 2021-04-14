package com.example.hotmartapp.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hotmartapp.R
import com.example.hotmartapp.data.model.Location

class RecyclerViewAdapter(
        val lista: ArrayList<Location>
) : RecyclerView.Adapter<RecyclerViewAdapter.MyHolder>() {

    class MyHolder(view: View): RecyclerView.ViewHolder(view) {
        val locationNameTextView = view.findViewById<TextView>(R.id.locationName)
        val locationTypeTextView = view.findViewById<TextView>(R.id.locationType)
        val locationReviewTextView = view.findViewById<TextView>(R.id.locationReview)

        fun bind(item: Location) {
            locationNameTextView.text = item.name
            locationTypeTextView.text = item.type
            locationReviewTextView.text = item.review.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder =
            MyHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_location, parent, false))

    override fun getItemCount(): Int = lista.size

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.bind(lista[position])
    }

}