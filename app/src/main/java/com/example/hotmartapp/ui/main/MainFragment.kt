package com.example.hotmartapp.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hotmartapp.R
import com.example.hotmartapp.data.model.*
import com.example.hotmartapp.ui.details.DetailsActivity
import org.koin.android.viewmodel.ext.android.viewModel

class MainFragment : Fragment(), OnLocationClickListener {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val mainViewModel: MainViewModel by viewModel()

    lateinit var myLocations: ArrayList<Location>
    lateinit var myImages: ArrayList<Image>

    lateinit var progressBar: ProgressBar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        view?.let {
            progressBar = it.findViewById(R.id.progressBar)
        }

        mainViewModel.states.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is MainViewState.ShowLocations -> showLocations(state.locations)
                is MainViewState.ShowError -> showError(state.error)
                is MainViewState.ShowImages -> showImages(state.images)
            }

        })

        mainViewModel.getImages()
    }

    private fun showLocations(locations: ArrayList<Location>) {
        myLocations = locations

        myLocations.forEachIndexed { index, location ->
            location.image = myImages[index]
        }

        val recyclerViewLocations = view?.findViewById<RecyclerView>(R.id.recyclerViewLocations)
        val locationsAdapter = RecyclerViewAdapter(myLocations, this)

        recyclerViewLocations?.let { recyclerView ->
            with(recyclerView) {
                layoutManager = GridLayoutManager(activity, 2)
                adapter = locationsAdapter
                setHasFixedSize(false)
                visibility = View.VISIBLE
            }
        }

        progressBar.visibility = View.GONE
    }

    private fun showError(error: String) {
        Toast.makeText(activity, error, Toast.LENGTH_LONG).show()
    }

    private fun showImages(images: ArrayList<Image>) {
        myImages = images
        mainViewModel.getLocations()
    }

    override fun onLocationClicked(location: Location) {
        val intent = Intent(activity, DetailsActivity::class.java)
        intent.putExtra("locationSelected", location)
        startActivity(intent)
    }

}