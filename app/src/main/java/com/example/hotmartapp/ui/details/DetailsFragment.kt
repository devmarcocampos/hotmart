package com.example.hotmartapp.ui.details

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.RatingBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hotmartapp.R
import com.example.hotmartapp.data.model.*
import com.example.hotmartapp.ui.main.MainViewState
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.squareup.picasso.Picasso
import org.koin.android.viewmodel.ext.android.viewModel
import java.io.IOException
import kotlin.collections.ArrayList


class DetailsFragment(
        private val locationSelected: Location
) : Fragment() {

    companion object {
        fun newInstance(locationSelected: Location) = DetailsFragment(locationSelected)
    }

    private val detailsViewModel: DetailsViewModel by viewModel()

    lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        view?.let {
            progressBar = it.findViewById(R.id.progressBar)
        }

        detailsViewModel.states.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is MainViewState.ShowLocationDetails -> showLocationDetails(state.locationDetails)
                is MainViewState.ShowFoods -> showFoods(state.foods)
            }
        })

        detailsViewModel.getLocationDetails(locationSelected.id)
    }

    private fun showLocationDetails(locationDetails: LocationDetails) {
        val txt = view?.findViewById<TextView>(R.id.nameLocation)
        txt?.text = locationDetails.name

        val ratingBar = view?.findViewById<RatingBar>(R.id.ratingBar)
        ratingBar?.rating = locationDetails.review.toFloat()

        val nota = view?.findViewById<TextView>(R.id.grade)
        nota?.text = locationDetails.review.toString()

        val aboutTextView = view?.findViewById<TextView>(R.id.aboutDescription)
        aboutTextView?.text = locationDetails.about

        val reviewsTextView = view?.findViewById<TextView>(R.id.reviewsDescription)
        reviewsTextView?.text = locationDetails.about

        val phoneTextView = view?.findViewById<TextView>(R.id.phoneTextView)
        phoneTextView?.text = locationDetails.phone

        val addressTextView = view?.findViewById<TextView>(R.id.addressTextView)
        addressTextView?.text = locationDetails.adress

        val image = view?.findViewById<ImageView>(R.id.imageLocation)

        val picasso = context?.let {
            Picasso.Builder(it).listener { _, _, exception ->
            exception?.printStackTrace()
            println("Picasso loading failed : ${exception?.message}")
            image?.setImageResource(R.drawable.ic_launcher_background)
        }.build()
        }

        picasso?.load(locationSelected.image.webformatURL)
                ?.fit()
                ?.into(image)

        handleSchedule(locationDetails.schedule)

        detailsViewModel.getFoods()
    }

    private fun handleSchedule(schedule: Any) {
        lateinit var locationSchedule: Schedule
        var scheduleDays = ArrayList<Day>()

        if (schedule is ArrayList<*>) {
            val gson = Gson()
            val itemType = object : TypeToken<List<Schedule>>() {}.type
            val itemList = gson.fromJson<List<Schedule>>(schedule.toString(), itemType)
            locationSchedule = itemList[0]
        } else {
            val arr: Schedule? = Gson().fromJson(schedule.toString(), Schedule::class.java)
            arr?.let {
                locationSchedule = it
            }
        }

        locationSchedule.monday?.let {
            it.name = "seg"
            scheduleDays.add(it)
        }

        locationSchedule.friday?.let {
            it.name = "sex"
            scheduleDays.add(it)
        }

        locationSchedule.saturday?.let {
            it.name = "sab"
            scheduleDays.add(it)
        }

        locationSchedule.sunday?.let {
            it.name = "dom"
            scheduleDays.add(it)
        }

        locationSchedule.thursday?.let {
            it.name = "qui"
            scheduleDays.add(it)
        }

        locationSchedule.tuesday?.let {
            it.name = "ter"
            scheduleDays.add(it)
        }

        locationSchedule.wednesday?.let {
            it.name = "qua"
            scheduleDays.add(it)
        }

        val recyclerViewSchedule = view?.findViewById<RecyclerView>(R.id.recyclerViewSchedule)
        val scheduleAdapter = RecyclerViewScheduleAdapter(scheduleDays)

        recyclerViewSchedule?.let { recyclerView ->
            with(recyclerView) {
                layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                adapter = scheduleAdapter
                setHasFixedSize(false)
            }
        }


        getComments()
    }

    private fun showFoods(foods: ArrayList<Image>) {
        val recyclerViewFotos = view?.findViewById<RecyclerView>(R.id.recyclerViewFotos)
        val fhotosAdapter = RecyclerViewFhotosAdapter(foods)

        recyclerViewFotos?.let { recyclerView ->
            with(recyclerView) {
                layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
                adapter = fhotosAdapter
                setHasFixedSize(false)
            }
        }

        progressBar.visibility = View.GONE

        val containerDetails = view?.findViewById<ConstraintLayout>(R.id.containerDetails)
        containerDetails?.visibility = View.VISIBLE
    }

    private fun getComments() {
        val jsonFileString = activity?.applicationContext?.let { getJsonDataFromAsset(it, "Commentsjson.json") }
        val responseComment: ResponseComment? = Gson().fromJson(jsonFileString.toString(), ResponseComment::class.java)

        responseComment?.let { response ->
            val recyclerViewComments = view?.findViewById<RecyclerView>(R.id.recyclerViewComments)
            val commentsAdapter = RecyclerViewCommentsAdapter(response.listComments)

            recyclerViewComments?.let { recyclerView ->
                with(recyclerView) {
                    layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                    adapter = commentsAdapter
                    setHasFixedSize(false)
                }
            }
        }
    }

    fun getJsonDataFromAsset(context: Context, fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }
}