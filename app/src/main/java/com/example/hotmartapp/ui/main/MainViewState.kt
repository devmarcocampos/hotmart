package com.example.hotmartapp.ui.main

import com.example.hotmartapp.data.model.Image
import com.example.hotmartapp.data.model.Location
import com.example.hotmartapp.data.model.LocationDetails

sealed class MainViewState {
    data class ShowLocations(val locations: ArrayList<Location>): MainViewState()
    data class ShowError(val error: String): MainViewState()
    data class ShowLocationDetails(val locationDetails: LocationDetails): MainViewState()
    data class ShowImages(val images: ArrayList<Image>): MainViewState()
    data class ShowFoods(val foods: ArrayList<Image>): MainViewState()
}