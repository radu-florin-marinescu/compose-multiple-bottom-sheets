package com.radumarinescu.multiplesheets.welcome

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.radumarinescu.multiplesheets.ui.theme.ComposeMultipleBottomsheetsTheme

@Composable
fun WelcomeScreen(
    viewModel: WelcomeViewModel = viewModel()
) {

}

@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    ComposeMultipleBottomsheetsTheme {
        WelcomeScreen(viewModel())
    }
}