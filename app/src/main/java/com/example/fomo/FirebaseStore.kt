package com.example.fomo

import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

class FirebaseStore {
    private val mFirestore=FirebaseFirestore.getInstance()
    fun registerUser(activity: SignupActivity,user: User)
    {
        mFirestore.collection("users").document(user.uId).set(user, SetOptions.merge()).addOnCompleteListener {task->
            if (task.isSuccessful)
            {
                activity.registerUserSuccess()
            }
            else
            {
                activity.displayErrorSnackbar(task.exception!!.message!!,true)
            }

        }
    }
}