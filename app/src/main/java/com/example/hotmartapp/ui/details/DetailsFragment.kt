package com.example.hotmartapp.ui.details

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import com.example.hotmartapp.R
import com.example.hotmartapp.data.model.LocationDetails
import com.example.hotmartapp.ui.main.MainViewModel
import com.example.hotmartapp.ui.main.MainViewState
import org.koin.android.viewmodel.ext.android.viewModel

class DetailsFragment : Fragment() {

    companion object {
        fun newInstance() = DetailsFragment()
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
            }
        })

        detailsViewModel.getLocationDetails(1)
    }

    fun showLocationDetails(locationDetails: LocationDetails) {
        val txt = view?.findViewById<TextView>(R.id.message)

        txt?.text = locationDetails.name
    }

}