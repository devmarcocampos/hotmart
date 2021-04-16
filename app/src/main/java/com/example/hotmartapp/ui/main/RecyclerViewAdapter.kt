package com.example.hotmartapp.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hotmartapp.R
import com.example.hotmartapp.data.model.Location
import com.squareup.picasso.Picasso

class RecyclerViewAdapter(
        val lista: ArrayList<Location>,
        val listener: OnLocationClickListener
) : RecyclerView.Adapter<RecyclerViewAdapter.MyHolder>() {

    class MyHolder(val view: View): RecyclerView.ViewHolder(view) {
        val locationImageView = view.findViewById<ImageView>(R.id.locationImage)
        val locationNameTextView = view.findViewById<TextView>(R.id.locationName)
        val locationTypeTextView = view.findViewById<TextView>(R.id.locationType)
        val locationReviewTextView = view.findViewById<TextView>(R.id.locationReview)

        fun bind(item: Location) {
            locationNameTextView.text = item.name
            locationTypeTextView.text = item.type
            locationReviewTextView.text = item.review.toString()

            val picasso = Picasso.Builder(view.context).listener { _, _, exception ->
                exception?.printStackTrace()
                println("Picasso loading failed : ${exception?.message}")
                locationImageView.setImageResource(R.drawable.ic_launcher_background)
            }.build()

//            picasso.load("https://pixabay.com/get/gb99a53a57ca642d1f80f1447ae583ac79614adb96b4adfaa77e616ab4f5a2cefd21f5c050661e355ed80357aec9b2b463e34ff76ae1949df0a4997b44ea25541_640.jpg")
//                    .into(locationImageView)

            picasso.load(item.image.webformatURL)
                    .fit()
                    .into(locationImageView)



//            Picasso.get().load("https://pixabay.com/get/gb99a53a57ca642d1f80f1447ae583ac79614adb96b4adfaa77e616ab4f5a2cefd21f5c050661e355ed80357aec9b2b463e34ff76ae1949df0a4997b44ea25541_640.jpg").into(locationImageView)
//            Picasso.get().load("https://pixabay.com/photos/sunflower-nature-flora-flower-3113318/").into(locationImageView)
            //
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder =
            MyHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_location, parent, false))

    override fun getItemCount(): Int = lista.size

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.bind(lista[position])
        holder.itemView.setOnClickListener {
            listener.onLocationClicked(lista[position])
        }
    }

}