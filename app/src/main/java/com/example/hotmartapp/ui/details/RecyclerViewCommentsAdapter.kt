package com.example.hotmartapp.ui.details

import android.R.attr.radius
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hotmartapp.R
import com.example.hotmartapp.data.model.Comment
import com.squareup.picasso.Picasso


class RecyclerViewCommentsAdapter(
    private val commentsList: ArrayList<Comment>
) : RecyclerView.Adapter<RecyclerViewCommentsAdapter.CommentsHolder>() {

    class CommentsHolder(val view: View): RecyclerView.ViewHolder(view) {
        val profileImageView = view.findViewById<ImageView>(R.id.profileImageView)
        val ratingBar = view.findViewById<RatingBar>(R.id.ratingBar)
        val commentTitleTextView = view.findViewById<TextView>(R.id.commentTitleTextView)
        val commentDescriptionTextView = view.findViewById<TextView>(R.id.commentDescriptionTextView)
        val commentOriginTextView = view.findViewById<TextView>(R.id.commentOriginTextView)

        fun bind(comment: Comment) {
            ratingBar.rating = comment.review.toFloat()
            commentTitleTextView.text = comment.title
            commentDescriptionTextView.text = comment.description
            commentOriginTextView.text = comment.origin

            val picasso = Picasso.Builder(view.context).listener { _, _, exception ->
                exception?.printStackTrace()
                println("Picasso loading failed : ${exception?.message}")
                profileImageView.setImageResource(R.drawable.ic_launcher_background)
            }.build()


            picasso.load(comment.author)
                .fit().centerCrop()
                .into(profileImageView)

//            picasso.load(comment.author)
//                .transform(RoundedCornersTransformation(radius, margin))

//            Picasso.with(activity).load(url).transform(CircleTransform()).into(imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsHolder =
        CommentsHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_comments, parent, false))

    override fun getItemCount(): Int = commentsList.size

    override fun onBindViewHolder(holder: CommentsHolder, position: Int) {
        holder.bind(commentsList[position])
    }
}