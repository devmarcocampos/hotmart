package com.example.hotmartapp.ui.main

import com.example.hotmartapp.data.model.Location

sealed class MainViewState {
    data class ShowLocations(val locations: ArrayList<Location>): MainViewState()
    data class ShowError(val error: String): MainViewState()
}