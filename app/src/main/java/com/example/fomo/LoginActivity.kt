package com.example.fomo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import com.airbnb.lottie.utils.Utils
import com.example.fomo.databinding.ActivityLoginBinding
import com.example.fomo.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : BaseActivity(), View.OnClickListener {
    lateinit var binding: ActivityLoginBinding
    lateinit var email:String
    lateinit var password: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnLogin.setOnClickListener(this)
        binding.txtRegister.setOnClickListener(this)
        binding.txtForgotPassword.setOnClickListener(this)

    }

    override fun onClick(view: View?) {
        when (view) {
            binding.txtRegister -> {
                startActivity(Intent(this@LoginActivity, SignupActivity::class.java))
            }
            binding.btnLogin -> {
                loginUser()

            }
            binding.txtForgotPassword->{startActivity(Intent(this@LoginActivity,ForgotPassword::class.java))}
        }
    }

    private fun loginUser() {
        if(validateUser())
        {
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password).addOnCompleteListener {task->
                if(task.isSuccessful)
                {
                    startActivity(Intent(this@LoginActivity,MainActivity::class.java))
                    finish()
                }
                else
                {
                    displayErrorSnackbar(task.exception!!.message!!,true)
                }
            }
        }
    }

    private fun validateUser(): Boolean {
        email=binding.etEmailId.text.toString().trim { it<=' ' }
        password=binding.etPassword.text.toString().trim { it<=' ' }
        return when{
            TextUtils.isEmpty(email)->{displayErrorSnackbar("Please Enter Valid Email Id",true)
                false
            }
            TextUtils.isEmpty(password)->{displayErrorSnackbar("Please Enter Password",true)
                false
            }
            else-> true
        }
    }
}