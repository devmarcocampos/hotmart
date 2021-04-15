package com.example.hotmartapp.ui.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hotmartapp.R

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.details_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, DetailsFragment.newInstance())
                .commitNow()
        }
    }
}