package com.tanu.shopsaathi.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tanu.shopsaathi.presentation.screen.splashandonboarding.OnBoardingScreen
import com.tanu.shopsaathi.presentation.screen.splashandonboarding.Splash

@Composable
fun Navigation (){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Splash.route) {
        composable(Screen.Splash.route) {
            Splash(navController = navController)
        }
        composable(Screen.OnBoarding.route) {
            OnBoardingScreen(navController = navController)
        }
    }

}