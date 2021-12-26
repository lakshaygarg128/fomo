package com.example.fomo


import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.example.fomo.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth

class SignupActivity : BaseActivity(), View.OnClickListener {
    lateinit var binding: ActivitySignupBinding
    lateinit var password: String
    lateinit var confirmPass: String
    lateinit var email: String
    lateinit var firstName: String
    lateinit var lastName: String
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
                        FirebaseStore().registerUser(
                            this@SignupActivity, User(
                                firebaseUser!!.uid,firstName, lastName, email
                            )
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
        val intent= Intent(this@SignupActivity,MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}