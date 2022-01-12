package com.example.fomo.ui

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.app.ActivityCompat
import com.example.fomo.R
import com.example.fomo.databinding.FragmentProfileBinding
import com.example.fomo.utils.Constants
import com.google.firebase.auth.FirebaseAuth

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    lateinit var binding:FragmentProfileBinding
    lateinit var sharedPreferences: SharedPreferences
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProfileBinding.bind(view)
        if (activity!=null)
        sharedPreferences= activity?.getSharedPreferences(Constants.SHARED_PREFERENCE,Context.MODE_PRIVATE)!!
        val userName=sharedPreferences.getString(Constants.USER_FIRST_NAME,null)+sharedPreferences.getString(Constants.USER_LAST_NAME,null)
        binding.name.text=userName
        binding.email.text=sharedPreferences.getString(Constants.USER_EMAIL,null)
        binding.city.text=sharedPreferences.getString(Constants.CITY,null)
        binding.temp.text=sharedPreferences.getString(Constants.TEMP,null)
        binding.logout.setOnClickListener {
            if (activity != null) {
                val alertDialog = AlertDialog.Builder(activity as Context)
                alertDialog.setTitle("Exit?")
                alertDialog.setMessage("Are You Sure You Want to Exit?")
                alertDialog.setPositiveButton("Yes"){text,listener->
                    FirebaseAuth.getInstance().signOut()
                    sharedPreferences.edit().clear().apply()
                    ActivityCompat.finishAffinity(activity as Activity)
                }
                alertDialog.setNegativeButton("No"){text,listener->}
                alertDialog.show()
            }
        }

        binding.cart.setOnClickListener{
            if(activity!=null)
            startActivity(Intent(activity as Context, OrderActivity::class.java))

        }

        binding.orders.setOnClickListener{
            if(activity!=null)
                startActivity(Intent(activity as Context, OrderActivity::class.java))

        }
        binding.favorites.setOnClickListener {
            if (activity!=null)
                startActivity(Intent(activity as Context, FavouritesActivity::class.java))
        }
    }

}