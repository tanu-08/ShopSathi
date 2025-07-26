package com.tanu.shopsaathi.presentation.screen.splashandonboarding

import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.OptIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.navigation.NavHostController
import com.tanu.shopsaathi.R
import com.tanu.shopsaathi.presentation.navigation.Screen
import kotlinx.coroutines.launch

@Composable
fun OnBoardingScreen(navController: NavHostController) {
    val onboardingTexts = listOf(
        "Welcome to ShopSaathi",
        "Find nearby trusted shops",
        "Track orders & savings easily",
        "Let's get started!"
    )

    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { onboardingTexts.size }
    )
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(0.dp, 40.dp, 0.dp, 40.dp)
            .background(Color.White, RectangleShape),
        verticalArrangement = Arrangement.Top
    ) {
        // Video (Top portion)
        ExoPlayerComposable(videoResId = R.raw.ecommerce_shopping_anim)
        Spacer(modifier = Modifier.weight(1f))

        // Text Pager
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) { page ->
            Text(
                text = onboardingTexts[page],
                style = MaterialTheme.typography.h6,
                textAlign = TextAlign.Center,
                color = com.tanu.shopsaathi.ui.theme.DarkBackground,
                modifier = Modifier.fillMaxWidth()
            )
        }

        // Indicator
        PagerIndicator(pagerState = pagerState, pageCount = onboardingTexts.size)
        // Button
        Button(
            onClick = {
                if (pagerState.currentPage == onboardingTexts.lastIndex) {
                    navController.navigate(Screen.LoginScreen.route) {
                        popUpTo(Screen.OnBoarding.route) { inclusive = true }
                    }
                } else {
                    scope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = com.tanu.shopsaathi.ui.theme.DarkBackground, // your desired background
                contentColor = Color.White // text/icon color
            )

        ) {
            Text(
                text = if (pagerState.currentPage == onboardingTexts.lastIndex)
                    "Get Started"
                else
                    "Next",
                color = Color.White,
                fontSize = 14.sp,
                style = MaterialTheme.typography.button


            )

        }
    }
}

@OptIn(UnstableApi::class)
@Composable
fun ExoPlayerComposable(videoResId: Int) {
    val context = LocalContext.current
    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            val mediaItem =
                MediaItem.fromUri("android.resource://${context.packageName}/$videoResId")
            setMediaItem(mediaItem)
            prepare()
            playWhenReady = true
            repeatMode = Player.REPEAT_MODE_ONE
        }
    }

    AndroidView(
        factory = {
            PlayerView(context).apply {
                player = exoPlayer
                useController = false
                resizeMode =
                    androidx.media3.ui.AspectRatioFrameLayout.RESIZE_MODE_ZOOM // CenterCrop style
                layoutParams = FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp)
            .padding(0.dp, 200.dp, 0.dp, 20.dp)
    )
}

@Composable
fun PagerIndicator(pagerState: PagerState, pageCount: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(pageCount) { index ->
            val isSelected = pagerState.currentPage == index
            Box(
                modifier = Modifier
                    .padding(horizontal = 4.dp, vertical = 20.dp)
                    .size(if (isSelected) 12.dp else 10.dp)
                    .clip(CircleShape)
                    .background(if (isSelected) Color.Black else Color.Gray)
            )
        }
    }
}
