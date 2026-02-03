package com.example.loginfirebaseretrofit

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.loginfirebaseretrofit.home.HomeScreen
import com.example.loginfirebaseretrofit.home.HomeViewModel
import com.example.loginfirebaseretrofit.login.ui.LoginScreen
import com.example.loginfirebaseretrofit.login.ui.LoginViewModel
import com.example.loginfirebaseretrofit.network.MarsApi
import com.example.loginfirebaseretrofit.ui.theme.LoginFirebaseRetrofitTheme
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {
    @SuppressLint("ViewModelConstructorInComposable")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var auth = FirebaseAuth.getInstance()
        val apiService = MarsApi.retrofitService

        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            LoginFirebaseRetrofitTheme {
                NavHost(navController = navController, startDestination = "home") {
                    composable("login") {
                        LoginScreen(LoginViewModel(), auth) {
                            navController.navigate("home")
                        }
                    }
                    composable("home") {
                        HomeScreen(HomeViewModel(apiService), auth)
                    }
                }
            }
        }
    }
}