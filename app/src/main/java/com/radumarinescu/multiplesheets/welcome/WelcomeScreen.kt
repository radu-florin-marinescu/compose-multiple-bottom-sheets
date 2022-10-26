package com.radumarinescu.multiplesheets.welcome

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.radumarinescu.multiplesheets.ui.theme.ComposeMultipleBottomsheetsTheme
import kotlinx.coroutines.CoroutineScope

@Composable
fun WelcomeScreen(
    viewModel: WelcomeViewModel = viewModel(),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
) {
    SheetScaffold(
        coroutineScope = coroutineScope,
        sheetContent = {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(350.dp)
            ) {
                Text(text = "My Bottom Sheet")
            }
        }
    ) { _, switchSheetVisibility ->
        Button(onClick = switchSheetVisibility) {
            Text(text = "Open")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    ComposeMultipleBottomsheetsTheme {
        WelcomeScreen(viewModel())
    }
}