package com.jeezzzz.vijayiwfhtechnologiestask.host

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.jeezzzz.vijayiwfhtechnologiestask.navigation.Navigation
import com.jeezzzz.vijayiwfhtechnologiestask.ui.theme.VijayiWFHTechnologiesTaskTheme
import com.jeezzzz.vijayiwfhtechnologiestask.viewModels.WatchModeViewModel
import org.koin.androidx.compose.getViewModel
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
        setContent {
            VijayiWFHTechnologiesTaskTheme {
                val viewModel: WatchModeViewModel = koinViewModel()
                Navigation(viewModel)
            }
        }
    }
}