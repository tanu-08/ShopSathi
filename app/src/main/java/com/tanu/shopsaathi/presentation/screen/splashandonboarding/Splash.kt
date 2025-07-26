package com.tanu.shopsaathi.presentation.screen.splashandonboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.tanu.shopsaathi.R
import com.tanu.shopsaathi.presentation.navigation.Screen
import kotlinx.coroutines.delay



@Composable
fun Splash(navController: NavHostController) {
    LaunchedEffect(Unit) {
        delay(2000)
        navController.navigate(Screen.OnBoarding.route) {
            popUpTo(Screen.Splash.route) { inclusive = true }
        }
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_app_logo),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )
    }
}

