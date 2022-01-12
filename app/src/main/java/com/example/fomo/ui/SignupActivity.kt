package com.example.fomo.ui


import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.example.fomo.BaseActivity
import com.example.fomo.FirebaseStore
import com.example.fomo.MainActivity
import com.example.fomo.User
import com.example.fomo.databinding.ActivitySignupBinding
import com.example.fomo.utils.Constants
import com.google.firebase.auth.FirebaseAuth

class SignupActivity : BaseActivity(), View.OnClickListener {
    lateinit var binding: ActivitySignupBinding
    lateinit var password: String
    lateinit var confirmPass: String
    lateinit var email: String
    lateinit var firstName: String
    lateinit var lastName: String
    lateinit var user: User
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnRegister.setOnClickListener(this)
        binding.txtLogin.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view) {
            binding.txtLogin -> {
                onBackPressed()
            }
            binding.btnRegister -> {
                registerUser()
            }
        }
    }

    private fun registerUser() {
        if (validateUser()) {
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val firebaseUser = task.result!!.user
                        user= User(
                            firebaseUser!!.uid,firstName, lastName, email
                        )
                        FirebaseStore().registerUser(
                            this@SignupActivity,user
                        )
                    } else {
                        displayErrorSnackbar(task.exception!!.message!!, true)
                    }
                }
        }
    }

    private fun validateUser(): Boolean {
        password = binding.etPassword.text.toString().trim { it <= ' ' }
        confirmPass = binding.etConfirmPassword.text.toString().trim { it <= ' ' }
        email = binding.etEmail.text.toString().trim { it <= ' ' }
        firstName = binding.etFirstName.text.toString().trim { it <= ' ' }
        lastName = binding.etLastName.text.toString().trim { it <= ' ' }
        return when {
            TextUtils.isEmpty(
                firstName
            ) -> {
                displayErrorSnackbar("Please Enter First Name", true)
                false
            }
            TextUtils.isEmpty(
                lastName
            ) -> {
                displayErrorSnackbar("Please Enter Last Name", true)
                false
            }
            TextUtils.isEmpty(
                email
            ) -> {
                displayErrorSnackbar("Please Enter Email ID", true)
                false
            }
            TextUtils.isEmpty(
                password
            ) -> {
                displayErrorSnackbar("Please Enter Password", true)
                false
            }
            confirmPass != password -> {
                displayErrorSnackbar("Passwords Don't Match", true)
                false
            }
            else -> true

        }
    }

    fun registerUserSuccess() {
        Toast.makeText(this@SignupActivity,"User Registered Successfully",Toast.LENGTH_LONG).show()
        sharedPreferences=getSharedPreferences(Constants.SHARED_PREFERENCE, Context.MODE_PRIVATE)
        sharedPreferences.edit().putString(Constants.USER_ID,user.uId).apply()
        sharedPreferences.edit().putString(Constants.USER_FIRST_NAME,user.firstName).apply()
        sharedPreferences.edit().putString(Constants.USER_LAST_NAME,user.lastName).apply()
        sharedPreferences.edit().putString(Constants.USER_EMAIL,user.email).apply()
        sharedPreferences.edit().putBoolean(Constants.USER_LOGIN,true).apply()
        val intent= Intent(this@SignupActivity, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}