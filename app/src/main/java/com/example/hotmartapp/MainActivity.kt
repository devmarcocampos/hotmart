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

            val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

//            val firstFragment=FirstFragment()
            val firstFragment = MainFragment.newInstance()
            val secondFragment=SecondFragment()
            val thirdFragment=ThirdFragment()

            setCurrentFragment(firstFragment)

            bottomNavigationView.setOnNavigationItemSelectedListener {
                when(it.itemId){
                    R.id.home -> {
//                        it.setIcon(R.drawable.ic_home_on)
                        setCurrentFragment(firstFragment)
                    }

                    R.id.map -> {
//                        it.setIcon(R.drawable.ic_map_on)
                        setCurrentFragment(secondFragment)
                    }

                    R.id.profile -> {
//                        it.setIcon(R.drawable.ic_profile_on)
                        setCurrentFragment(thirdFragment)
                    }

                }
                true
            }




            //
//            supportFragmentManager.beginTransaction()
//                    .replace(R.id.container, MainFragment.newInstance())
//                    .commitNow()
        }
    }

    fun setCurrentFragment (fragment:Fragment) =
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.mainContainer,fragment)
                addToBackStack(null)
                commit()
            }

    fun goToDetailsActivity() {

    }
}

class FirstFragment: Fragment(R.layout.fragment_first) {
}

class SecondFragment:Fragment(R.layout.fragment_second) {
}

class ThirdFragment:Fragment(R.layout.fragment_third) {
}