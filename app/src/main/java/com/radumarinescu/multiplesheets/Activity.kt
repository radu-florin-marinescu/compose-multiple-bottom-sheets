package com.radumarinescu.multiplesheets

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.radumarinescu.multiplesheets.ui.theme.ComposeMultipleBottomsheetsTheme
import com.radumarinescu.multiplesheets.welcome.WelcomeScreen

class Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeMultipleBottomsheetsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    WelcomeScreen()
                }
            }
        }
    }
}
