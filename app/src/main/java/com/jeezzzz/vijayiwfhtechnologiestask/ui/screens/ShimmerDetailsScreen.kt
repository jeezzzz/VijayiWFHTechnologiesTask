package com.jeezzzz.vijayiwfhtechnologiestask.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.shimmer

@Composable
fun ShimmerDetailsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
    ) {
        ShimmerBox(modifier = Modifier.fillMaxWidth().height(400.dp))

        Spacer(modifier = Modifier.height(16.dp))

        ShimmerBox(modifier = Modifier.width(200.dp).height(30.dp))

        Spacer(modifier = Modifier.height(12.dp))

        ShimmerBox(modifier = Modifier.fillMaxWidth().height(80.dp))

        Spacer(modifier = Modifier.height(16.dp))

        repeat(3) {
            ShimmerBox(modifier = Modifier.fillMaxWidth().height(20.dp))
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun ShimmerBox(modifier: Modifier) {
    val shimmerColors = listOf(
        Color.DarkGray.copy(alpha = 0.6f),
        Color.Black.copy(alpha = 0.4f),
        Color.DarkGray.copy(alpha = 0.6f)
    )

    Box(
        modifier = modifier
            .shimmer()
            .background(
                brush = Brush.linearGradient(
                    colors = shimmerColors,
                ),

                shape = RoundedCornerShape(8.dp)
            )
    )
}