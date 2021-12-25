package com.example.fomo

import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar

open class BaseActivity : AppCompatActivity() {
    fun displayErrorSnackbar(msg:String,error:Boolean)
    {
        val snackbar=Snackbar.make(findViewById(android.R.id.content),msg,Snackbar.LENGTH_LONG)
        val snackbarView=snackbar.view
        if(error)
        snackbarView.setBackgroundColor(ContextCompat.getColor(this,R.color.red))
        else
        snackbarView.setBackgroundColor(ContextCompat.getColor(this,R.color.green))
        snackbar.show()
    }
}