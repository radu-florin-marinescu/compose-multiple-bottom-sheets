package com.radumarinescu.multiplesheets.welcome

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
    var visible by remember { mutableStateOf(true) }

    LaunchedEffect(bottomSheetScaffoldState) {
        snapshotFlow { bottomSheetScaffoldState.bottomSheetState.targetValue }.collect { isExpanded ->
            visible = !visible
            Log.i("SHEET", "Expanded: $isExpanded")
        }
    }
    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetPeekHeight = 0.dp,
        sheetElevation = 40.dp,
        sheetGesturesEnabled = true,
        sheetShape = RoundedCornerShape(
            topStart = 20.dp,
            topEnd = 20.dp
        ),
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
            Box(modifier = Modifier.fillMaxSize()) {
                AnimatedVisibility(
                    visible = visible,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Black.copy(alpha = 0.2f))
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null,
                            ){
                                coroutineScope.switchBottomSheetVisibility(bottomSheetScaffoldState)
                            }
                    )
                }
                Button(onClick = {
                    coroutineScope.switchBottomSheetVisibility(bottomSheetScaffoldState)
                }) {
                    Text(text = "Open")
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterialApi::class)
private fun CoroutineScope.switchBottomSheetVisibility(state: BottomSheetScaffoldState) {
    this.launch {
        if (state.bottomSheetState.isCollapsed) {
            state.bottomSheetState.expand()
        } else {
            state.bottomSheetState.collapse()
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