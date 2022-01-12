package com.example.fomo


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.fomo.databinding.ActivityMainBinding
import com.example.fomo.ui.LocationFragment
import com.example.fomo.ui.ProfileFragment
import com.example.fomo.ui.WeatherFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {


    private var binding : ActivityMainBinding? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      //  binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
       // replaceFragment(Mood())
        var bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavView)
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.menu_profile -> replaceFragment(ProfileFragment())
                R.id.weather_menu -> replaceFragment(WeatherFragment())
                R.id.menu_location-> replaceFragment(LocationFragment())
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        if(fragment != null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainer, fragment)
            transaction.commit()
        }
    }
}