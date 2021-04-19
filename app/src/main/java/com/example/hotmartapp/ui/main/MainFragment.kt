package com.example.hotmartapp.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hotmartapp.MainActivity
import com.example.hotmartapp.R
import com.example.hotmartapp.data.model.*
import com.example.hotmartapp.ui.details.DetailsActivity
import com.example.hotmartapp.ui.details.DetailsFragment
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel
import java.io.IOException

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


//        val jsonFileString = activity?.applicationContext?.let { getJsonDataFromAsset(it, "Testjson.json") }
//        val arr: ResponseLocation? = Gson().fromJson(jsonFileString.toString(), ResponseLocation::class.java)
//
//        arr?.let {
//            val a = it.listLocations[0].name
//        }

//        val jsonFileString = activity?.applicationContext?.let { getJsonDataFromAsset(it, "Commentsjson.json") }
//        val arr: ResponseComment? = Gson().fromJson(jsonFileString.toString(), ResponseComment::class.java)
//
//        arr?.let {
//            val a = it.listComments[0].description
//        }


//        val gson = Gson()
//        val listPersonType = object : TypeToken<List<Person>>() {}.type
//
//        var persons: List<Person> = gson.fromJson(jsonFileString, listPersonType)

//        val testJson = TestJson.json

//        val arr: ResponseLocation? = Gson().fromJson(TestJson)
//        val arr: Schedule? = Gson().fromJson(schedule.toString(), Schedule::class.java)

        mainViewModel.states.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is MainViewState.ShowLocations -> showLocations(state.locations)
                is MainViewState.ShowError -> showError(state.error)
                is MainViewState.ShowImages -> showImages(state.images)
            }

        })

//        CoroutineScope()

//        val uiScope: CoroutineScope = MainScope()
//        uiScope.launch {
//            mainViewModel.getImages().await
//        }

        mainViewModel.getImages()

    }

//    fun getJsonDataFromAsset(context: Context, fileName: String): String? {
//        val jsonString: String
//        try {
//            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
//        } catch (ioException: IOException) {
//            ioException.printStackTrace()
//            return null
//        }
//        return jsonString
//    }

    private fun showLocations(locations: ArrayList<Location>) {
//        val txt = view?.findViewById<TextView>(R.id.message)
//        txt?.text = locations[0].name

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
//        recyclerViewLocations?.visibility = View.VISIBLE
    }

    private fun showError(error: String) {
        Toast.makeText(activity, error, Toast.LENGTH_LONG).show()
    }

    private fun showImages(images: ArrayList<Image>) {
//        Toast.makeText(activity, images[0].type, Toast.LENGTH_LONG).show()

        myImages = images
        mainViewModel.getLocations()


//        for (0...loc in myLocations) {
//            lo
//        }
//
//        arr.forEachIndexed { index, e ->
//            println("$e at $index")
//        }

//        myLocations.forEachIndexed { index, location ->
//            location.image = images[index]
//        }

    }

    override fun onLocationClicked(location: Location) {
//        Toast.makeText(activity, location.name, Toast.LENGTH_LONG).show()

        val intent = Intent(activity, DetailsActivity::class.java)
        intent.putExtra("locationSelected", location)
        startActivity(intent)

//        (activity as MainActivity).setCurrentFragment(DetailsFragment.newInstance(location))
    }

}