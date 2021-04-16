package com.example.hotmartapp.ui.details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.hotmartapp.R
import com.example.hotmartapp.data.model.Image
import com.squareup.picasso.Picasso

class RecyclerViewFhotosAdapter(
        private val fhotos: ArrayList<Image>
) : RecyclerView.Adapter<RecyclerViewFhotosAdapter.FhotosHolder>() {

    class FhotosHolder(val view: View): RecyclerView.ViewHolder(view) {
        val foodImage = view.findViewById<ImageView>(R.id.foodImage)

        fun bind(fhoto: Image) {
            val picasso = Picasso.Builder(view.context).listener { _, _, exception ->
                exception?.printStackTrace()
                println("Picasso loading failed : ${exception?.message}")
                foodImage.setImageResource(R.drawable.ic_launcher_background)
            }.build()

            picasso.load(fhoto.webformatURL)
                    .fit()
                    .into(foodImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FhotosHolder =
            FhotosHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_photo, parent, false))

    override fun getItemCount(): Int = fhotos.size

    override fun onBindViewHolder(holder: FhotosHolder, position: Int) {
        holder.bind(fhotos[position])
    }

}