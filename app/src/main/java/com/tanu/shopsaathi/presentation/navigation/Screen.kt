package com.tanu.shopsaathi.presentation.navigation

sealed class Screen(val route:String) {
    object Splash : Screen("splash_screen")
    object OnBoarding : Screen("onboarding_screen")
    object LoginScreen : Screen("login_screen")
    object SignUpScreen : Screen("signup_screen")
    object HomeScreen : Screen("home_screen")
}