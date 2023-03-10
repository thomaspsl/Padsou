package com.example.padsou.models

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.NotificationCompat
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.padsou.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

// ViewModel : voir homeViewModel
class AuthViewModel: ViewModel() {

    // Login
    fun login(email: String, password: String, navController: NavController){
        Firebase.auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                Log.d("user", task.result.user?.uid ?:"")
                val user = Firebase.auth.currentUser
                Log.d("user","Identifiant : {${user?.uid}}")
                navController.navigate("home")

            }
        }
    }
    // Auto Login
    /*fun autologin(auth: String, navController: NavController): String {
        return if(auth != ""){
            // Sign in success, update UI with the signed-in user's information
            Log.d("user","Identifiant : {${auth}}")
            "home"
            //updateUI(user)
        } else {
            // If sign in fails, display a message to the user.
            Log.w("user", "signInToken:failure")
            "onBoarding"
            //updateUI(null)
        }
    }*/

    // Register
    fun register(mail:String,password:String,navController: NavController){
        Firebase.auth.createUserWithEmailAndPassword(mail, password).addOnCompleteListener() { task ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                Log.d(TAG, "createUserWithEmail:success")
                navController.navigate("login")

                //updateUI(user)
            }
        }
    }
}