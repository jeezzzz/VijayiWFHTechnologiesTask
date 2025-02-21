package com.jeezzzz.vijayiwfhtechnologiestask.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.jeezzzz.vijayiwfhtechnologiestask.viewModels.WatchModeViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailsScreen(navController: NavController, viewModel: WatchModeViewModel, id: Int) {
    val titleDetails by viewModel.titleDetails.collectAsState(initial = null)
    var isLoading by remember { mutableStateOf(true) } // 👈 Track loading state
    val headingColor = Color(0xFF8A00C4)

    LaunchedEffect(id) {
        isLoading = true
        viewModel.fetchTitleDetails(id)
        isLoading = false
    }

    if (isLoading||titleDetails==null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            ShimmerDetailsScreen()
        }
    } else {
        val details = titleDetails!!

        Box(modifier = Modifier.fillMaxSize().background(Color.Black)) {
            // Background Image that covers the top
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(details.posterLarge),
                    contentDescription = details.title,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                // Gradient Overlay at the Bottom of Image for Fading Effect
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .align(Alignment.BottomCenter)
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color.Transparent, Color.Black),
                                startY = 0f,
                                endY = 300f
                            )
                        )
                )
            }

            // Back Button overlapping the image
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .padding(16.dp)
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color.Black.copy(alpha = 0.6f))
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }

            // Main content
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(top = 350.dp, start = 16.dp, end = 16.dp, bottom = 16.dp) // Shift below image
            ) {
                Text(
                    text = details.title,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Read More Functionality
                var expanded by remember { mutableStateOf(false) }
                val maxLines = if (expanded) Int.MAX_VALUE else 3

                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = details.plot_overview,
                        fontSize = 16.sp,
                        color = Color.White.copy(alpha = 0.9f),
                        maxLines = maxLines,
                        overflow = TextOverflow.Ellipsis,
                    )

                    Text(
                        text = if (expanded) "Read Less" else "Read More",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = headingColor,
                        modifier = Modifier
                            .clickable { expanded = !expanded }
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))
                Divider(color = Color.Gray, thickness = 1.dp)

                // Additional Details
                DetailItem(label = "Year", value = details.year.toString())
                DetailItem(label = "Genres", value = details.genre_names.joinToString(", "))
                DetailItem(label = "User Rating", value = "${details.user_rating}/10")
            }
        }
    }
}

@Composable
fun DetailItem(label: String, value: String) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
    ) {
        Text(
            text = label,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0x922323FF)
        )
        Text(
            text = value,
            fontSize = 16.sp,
            color = Color.White
        )
    }
}
