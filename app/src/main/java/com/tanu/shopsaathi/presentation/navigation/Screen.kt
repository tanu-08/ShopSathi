package com.tanu.shopsaathi.presentation.navigation

sealed class Screen(val route:String) {
    object Splash : Screen("splash_screen")
    object OnBoarding : Screen("onboarding_screen")
    object Login : Screen("login_screen")
    object Register : Screen("register_screen")
    object Home : Screen("home_screen")
}