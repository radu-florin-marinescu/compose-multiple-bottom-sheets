package com.radumarinescu.multiplesheets.welcome

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.radumarinescu.multiplesheets.ui.theme.ComposeMultipleBottomsheetsTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun WelcomeScreen(
    viewModel: WelcomeViewModel = viewModel(),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
) {
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )
    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetPeekHeight = 0.dp,
        sheetContent = {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(350.dp)
            ) {
                Text(text = "My Bottom Sheet")
            }
        },
        content = {
            Button(onClick = {
                coroutineScope.launch {
                    if (bottomSheetScaffoldState.bottomSheetState.isCollapsed) {
                        bottomSheetScaffoldState.bottomSheetState.expand()
                    } else {
                        bottomSheetScaffoldState.bottomSheetState.collapse()
                    }
                }
            }) {
                Text(text = "Open")
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    ComposeMultipleBottomsheetsTheme {
        WelcomeScreen(viewModel())
    }
}