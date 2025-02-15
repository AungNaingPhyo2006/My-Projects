package com.example.kotlinplayground

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import androidx.compose.animation.core.Animatable
import androidx.compose.ui.draw.rotate

@Composable
fun SimpleAnimation(modifier: Modifier = Modifier) {
    val scale = remember { Animatable(0f) }
    var animateAgain = remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    // Start animation when this composable is launched or state changes
    LaunchedEffect(key1 = animateAgain.value) {
        scale.animateTo(
            targetValue = 360f,
            animationSpec = tween(
                durationMillis = 2000,
                easing = { OvershootInterpolator(2f).getInterpolation(it) }
            )
        )
    }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .size(200.dp)
                .rotate(scale.value),
            painter = painterResource(id = R.drawable.mom_image),
            contentDescription = "Animated Image"
        )

        Button(onClick = {
            coroutineScope.launch {
                scale.snapTo(0f) // Reset animation state
            }
            animateAgain.value = !animateAgain.value // Trigger reanimation
        }) {
            Text(text = "Animate")
        }
    }
}
