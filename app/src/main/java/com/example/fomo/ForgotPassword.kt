package com.example.fomo


import android.os.Bundle
import android.text.TextUtils
import android.view.View
import com.example.fomo.databinding.ActivityForgotPasswordBinding
import com.google.firebase.auth.FirebaseAuth

class ForgotPassword : BaseActivity() {
    lateinit var binding:ActivityForgotPasswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnResetPass.setOnClickListener {
            val email=binding.etEmail.text.toString().trim { it<=' ' }
            if(TextUtils.isEmpty(email))
                displayErrorSnackbar("Please Enter Email Id",true)
            else
            {
                FirebaseAuth.getInstance().sendPasswordResetEmail(email).addOnCompleteListener {task->
                if(task.isSuccessful)
                {
                    binding.etEmail.visibility= View.GONE
                    binding.btnResetPass.visibility=View.GONE
                    binding.textForgotPass.setText(getString(R.string.reset_email_sent))
                }
                    else
                        displayErrorSnackbar(task.exception!!.message!!,true)
                }

            }
        }

    }
}