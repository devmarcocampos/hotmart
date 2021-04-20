package com.example.hotmartapp.ui.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.widget.Toolbar
import com.example.hotmartapp.R
import com.example.hotmartapp.data.model.Location

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.details_activity)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val backButton = findViewById<Button>(R.id.backButton)
        backButton.setOnClickListener {
            super.onBackPressed()
        }

        val shareButton = findViewById<Button>(R.id.shareButton)
        shareButton.setOnClickListener {
            var fragment = supportFragmentManager.findFragmentByTag("fragmentDetails") as DetailsFragment
            fragment.shareImage()
        }

        val locationSelected = intent.getSerializableExtra("locationSelected") as? Location

        if (savedInstanceState == null) {
            locationSelected?.let { DetailsFragment.newInstance(it) }?.let {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, it, "fragmentDetails")
                    .commitNow()
            }
        }
    }
}