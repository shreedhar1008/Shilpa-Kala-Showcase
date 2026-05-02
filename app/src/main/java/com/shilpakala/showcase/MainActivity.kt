package com.shilpakala.showcase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.shilpakala.showcase.presentation.navigation.NavGraph
import com.shilpakala.showcase.presentation.theme.CreamWhite
import com.shilpakala.showcase.presentation.theme.ShilpaKalaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShilpaKalaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = CreamWhite
                ) {
                    NavGraph()
                }
            }
        }
    }
}
