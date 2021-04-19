package com.example.hotmartapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.hotmartapp.ui.main.MainFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {

            supportActionBar?.title = "Home"

            val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

            val firstFragment = MainFragment.newInstance()
            val secondFragment=SecondFragment()
            val thirdFragment=ThirdFragment()

            setCurrentFragment(firstFragment)

            bottomNavigationView.setOnNavigationItemSelectedListener {
                when(it.itemId){
                    R.id.home -> {
                        setCurrentFragment(firstFragment)
                    }
                    R.id.map -> {
                        setCurrentFragment(secondFragment)
                    }
                    R.id.profile -> {
                        setCurrentFragment(thirdFragment)
                    }
                }
                true
            }
        }
    }

    fun setCurrentFragment (fragment:Fragment) =
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.mainContainer,fragment)
                addToBackStack(null)
                commit()
            }
}

class FirstFragment: Fragment(R.layout.fragment_first) {
}

class SecondFragment:Fragment(R.layout.fragment_second) {
}

class ThirdFragment:Fragment(R.layout.fragment_third) {
}