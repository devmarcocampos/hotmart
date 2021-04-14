package com.example.hotmartapp.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hotmartapp.R
import com.example.hotmartapp.data.model.Location
import org.koin.android.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mainViewModel.states.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is MainViewState.ShowLocations -> showLocations(state.locations)
                is MainViewState.ShowError -> showError(state.error)
            }

        })

        mainViewModel.getLocations()
    }

    private fun showLocations(locations: ArrayList<Location>) {
//        val txt = view?.findViewById<TextView>(R.id.message)
//        txt?.text = locations[0].name

        val recyclerViewLocations = view?.findViewById<RecyclerView>(R.id.recyclerViewLocations)
        val locationsAdapter = RecyclerViewAdapter(locations)

        recyclerViewLocations?.let { recyclerView ->
            with(recyclerView) {
                layoutManager = GridLayoutManager(activity, 2)
                adapter = locationsAdapter
                setHasFixedSize(false)
            }
        }
    }

    private fun showError(error: String) {
        Toast.makeText(activity, error, Toast.LENGTH_LONG).show()
    }

}