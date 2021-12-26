package com.example.fomo

import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
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
    fun getCurrentUserId():String
    {
        return FirebaseAuth.getInstance().currentUser!!.uid
    }
    fun getUserDetails(activity: LoginActivity)
    {
        mFirestore.collection("users").document(getCurrentUserId()).get().addOnSuccessListener {document->
                val user:User=document.toObject(User::class.java)!!
                activity.gotUserDetails(user)
            }.addOnFailureListener {
                activity.displayErrorSnackbar(it.message!!,true)
        }


    }
}