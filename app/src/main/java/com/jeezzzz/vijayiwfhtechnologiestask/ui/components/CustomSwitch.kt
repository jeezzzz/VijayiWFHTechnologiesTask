package com.jeezzzz.vijayiwfhtechnologiestask.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomSwitch(
    isMoviesSelected: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    val gradientBrush = Brush.linearGradient(
        colors = listOf(Color(0xFF2323FF), Color(0xFF8A00C4), Color(0xFFfe019a))
    )

    val unselectedBorderColor = Color.White
    val buttonColor = Color.Transparent

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(4.dp)
            .background(Color.Transparent)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .background(if (isMoviesSelected) Color.Transparent else buttonColor, CircleShape)
                    .border(
                        width = if (isMoviesSelected) 0.dp else 2.dp,
                        color = unselectedBorderColor,
                        shape = CircleShape
                    )
                    .clickable { onCheckedChange(true) },
                contentAlignment = Alignment.Center
            ) {
                if (isMoviesSelected) {
                    Box(
                        modifier = Modifier
                            .matchParentSize()
                            .border(
                                width = 3.dp,
                                brush = gradientBrush,
                                shape = CircleShape
                            )
                            .padding(2.dp)
                            .background(buttonColor, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "Movies", fontSize = 18.sp, color = Color.White)
                    }
                } else {
                    Text(text = "Movies", fontSize = 18.sp, color = Color.Gray)
                }
            }

            Spacer(Modifier.width(10.dp))

            // TV Shows Button
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .background(if (!isMoviesSelected) Color.Transparent else buttonColor, CircleShape)
                    .border(
                        width = if (!isMoviesSelected) 0.dp else 2.dp,
                        color = unselectedBorderColor,
                        shape = CircleShape
                    )
                    .clickable { onCheckedChange(false) },
                contentAlignment = Alignment.Center
            ) {
                if (!isMoviesSelected) {
                    Box(
                        modifier = Modifier
                            .matchParentSize()
                            .border(
                                width = 3.dp,
                                brush = gradientBrush,
                                shape = CircleShape
                            )
                            .padding(2.dp)
                            .background(buttonColor, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "TV Shows", fontSize = 18.sp, color = Color.White)
                    }
                } else {
                    Text(text = "TV Shows", fontSize = 18.sp, color = Color.Gray)
                }
            }
        }
    }
}

