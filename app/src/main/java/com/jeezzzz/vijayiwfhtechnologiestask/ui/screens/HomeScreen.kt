package com.jeezzzz.vijayiwfhtechnologiestask.ui.screens

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.jeezzzz.vijayiwfhtechnologiestask.ui.components.CustomSwitch
import com.jeezzzz.vijayiwfhtechnologiestask.ui.components.TitleItem
import com.valentinilk.shimmer.shimmer
import com.jeezzzz.vijayiwfhtechnologiestask.viewModels.WatchModeViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(navController: NavController, viewModel: WatchModeViewModel) {
    val moviesAndTvShows by viewModel.moviesAndTvShows.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()
    val isLoading = moviesAndTvShows == null
    var isMoviesSelected by remember { mutableStateOf(true) }
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    fun isInternetAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
    }

    LaunchedEffect(Unit) {
        if (!isInternetAvailable(context)) {
            scope.launch {
                snackbarHostState.showSnackbar("No internet connection", duration = SnackbarDuration.Short, withDismissAction = true)
            }
        } else {
            viewModel.fetchMoviesAndTvShows()
        }
    }

    val gradient = Brush.radialGradient(
        colors = listOf(
            Color(0x242323FF),
            Color(0x24fe019a),
            Color.Black
        ),
        radius = 800.0f,
    )

    Column(modifier = Modifier.fillMaxSize().background(Color.Black)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(65.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Movies & TV Shows",
                fontSize = 24.sp,
                color = Color.White
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            CustomSwitch(
                isMoviesSelected = isMoviesSelected,
                onCheckedChange = { isMoviesSelected = it }
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(gradient)
        ) {
            if (isLoading) {
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(minSize = 120.dp),
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(8.dp)
                ) {
                    items(10) {
                        Box(
                            modifier = Modifier
                                .height(180.dp)
                                .fillMaxWidth()
                                .padding(8.dp)
                                .shimmer()
                                .background(Color.Gray.copy(alpha = 0.2f))
                        )
                    }
                }
            } else {
                val (movies, tvShows) = moviesAndTvShows!!
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(minSize = 120.dp),
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(8.dp)
                ) {
                    val data = if (isMoviesSelected) movies else tvShows
                    items(data) { title ->
                        TitleItem(title) {
                            scope.launch {
                                delay(300)
                                navController.navigate("details/${title.id}")
                            }
                        }
                    }
                }
            }

            errorMessage?.let {
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            }
        }
    }

    SnackbarHost(hostState = snackbarHostState) {
        Snackbar(
            snackbarData = it,
            containerColor = Color.Red,
            contentColor = Color.White
        )
    }
}
