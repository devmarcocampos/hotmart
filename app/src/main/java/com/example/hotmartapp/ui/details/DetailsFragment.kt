package com.example.hotmartapp.ui.details

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hotmartapp.R
import com.example.hotmartapp.data.model.Image
import com.example.hotmartapp.data.model.Location
import com.example.hotmartapp.data.model.LocationDetails
import com.example.hotmartapp.ui.main.MainViewModel
import com.example.hotmartapp.ui.main.MainViewState
import com.example.hotmartapp.ui.main.RecyclerViewAdapter
import com.squareup.picasso.Picasso
import org.koin.android.viewmodel.ext.android.viewModel

class DetailsFragment(
        private val locationSelected: Location
) : Fragment() {

    companion object {
        fun newInstance(locationSelected: Location) = DetailsFragment(locationSelected)
    }

    private val detailsViewModel: DetailsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        detailsViewModel.states.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is MainViewState.ShowLocationDetails -> showLocationDetails(state.locationDetails)
                is MainViewState.ShowFoods -> showFoods(state.foods)
            }
        })

//        detailsViewModel.getLocationDetails(1)
        detailsViewModel.getLocationDetails(locationSelected.id)

        detailsViewModel.getFoods()
    }

    private fun showLocationDetails(locationDetails: LocationDetails) {
        val txt = view?.findViewById<TextView>(R.id.nameLocation)
        txt?.text = locationDetails.name

        val nota = view?.findViewById<TextView>(R.id.grade)
        nota?.text = locationDetails.review.toString()

        val aboutTextView = view?.findViewById<TextView>(R.id.aboutDescription)
        aboutTextView?.text = locationDetails.about

        val reviewsTextView = view?.findViewById<TextView>(R.id.reviewsDescription)
        reviewsTextView?.text = locationDetails.about

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
    }

    private fun showFoods(foods: ArrayList<Image>) {
//        val fotos = view?.findViewById<TextView>(R.id.fotos)
//        fotos?.text = foods[0].id.toString()

        val recyclerViewFotos = view?.findViewById<RecyclerView>(R.id.recyclerViewFotos)
        val fhotosAdapter = RecyclerViewFhotosAdapter(foods)

        recyclerViewFotos?.let { recyclerView ->
            with(recyclerView) {
                layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
                adapter = fhotosAdapter
                setHasFixedSize(false)
            }
        }
    }

}