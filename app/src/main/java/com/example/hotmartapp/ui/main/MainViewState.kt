package com.example.hotmartapp.ui.main

import com.example.hotmartapp.data.model.Location
import com.example.hotmartapp.data.model.LocationDetails

sealed class MainViewState {
    data class ShowLocations(val locations: ArrayList<Location>): MainViewState()
    data class ShowError(val error: String): MainViewState()
    data class ShowLocationDetails(val locationDetails: LocationDetails): MainViewState()
}