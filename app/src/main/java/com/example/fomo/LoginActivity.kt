package com.example.fomo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import com.airbnb.lottie.utils.Utils
import com.example.fomo.databinding.ActivityLoginBinding
import com.example.fomo.databinding.ActivityMainBinding

class LoginActivity : BaseActivity(), View.OnClickListener {
    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onClick(view: View?) {
        when (view) {
            binding.txtRegister -> {
                startActivity(Intent(this@LoginActivity, SignupActivity::class.java))
            }
            binding.btnLogin -> {
                loginUser()

            }
        }
    }

    private fun loginUser() {
        if(validateUser())
        {

        }
    }

    private fun validateUser(): Boolean {
        return when{
            TextUtils.isEmpty(binding.etEmailId.text.toString().trim { it<=' ' })->{displayErrorSnackbar("Please Enter Valid Email Id",true)
                false
            }
            TextUtils.isEmpty(binding.etPassword.text.toString().trim { it<=' ' })->{displayErrorSnackbar("Please Enter Password",true)
                false
            }
            else-> true
        }
    }
}