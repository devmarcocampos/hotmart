package com.example.hotmartapp.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hotmartapp.data.repository.DetailsRepository
import com.example.hotmartapp.ui.main.BaseViewModel
import com.example.hotmartapp.ui.main.MainViewState
import kotlinx.coroutines.launch
import java.lang.Exception

class DetailsViewModel(private val detailsRepository: DetailsRepository) : BaseViewModel() {
    private val _states = MutableLiveData<MainViewState>()
    val states: LiveData<MainViewState>
        get() =_states

    fun getLocationDetails(id: Int) {
        launch {
            try {
                val response = detailsRepository.getLocationDetails(id)
                println(response)
                _states.value = MainViewState.ShowLocationDetails(response)
            } catch (exception: Exception) {
                _states.value = MainViewState.ShowError(exception.toString())
            }
        }
    }
}