package com.example.hotmartapp.ui.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hotmartapp.R
import com.example.hotmartapp.data.model.Image
import com.example.hotmartapp.data.model.Location

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.details_activity)

        val locationSelected = intent.getSerializableExtra("locationSelected") as? Location

        if (savedInstanceState == null) {
            locationSelected?.let { DetailsFragment.newInstance(it) }?.let {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, it)
                    .commitNow()
            }
        }
    }
}