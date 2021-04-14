package com.example.hotmartapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hotmartapp.data.repository.MainRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel(private val mainRepository: MainRepository) : BaseViewModel() {
    private val _states = MutableLiveData<MainViewState>()
    val states: LiveData<MainViewState>
        get() =_states

    fun getLocations() {
        launch {
            try {
                val response = mainRepository.getLocations()
                _states.value = MainViewState.ShowLocations(response.listLocations)
            } catch (exception: Exception) {
                _states.value = MainViewState.ShowError(exception.toString())
            }
        }
    }
}