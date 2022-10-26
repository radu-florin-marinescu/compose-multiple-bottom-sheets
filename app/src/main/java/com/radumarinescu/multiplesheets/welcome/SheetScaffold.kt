package com.radumarinescu.multiplesheets.welcome

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * @param modifier -> acts as the base modifier for the content frame
 * @param sheetContent -> composable for the contents of the bottom sheet
 * @param content -> composable for the contents of the screen behind the bottom sheet, includes
 * the paddingValues provided by the scaffold as well as a callback function for external trigger
 * of the show/hide events for our bottom sheet.
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SheetScaffold(
    coroutineScope: CoroutineScope,
    modifier: Modifier = Modifier,
    sheetContent: @Composable ColumnScope.() -> Unit,
    content: @Composable (PaddingValues, () -> Unit) -> Unit
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
        sheetContent = sheetContent,
        content = { paddingValues ->
            Box(modifier = modifier) {
                DimmedBackground(visible = visible) {
                    coroutineScope.switchBottomSheetVisibility(bottomSheetScaffoldState)
                }
                content(paddingValues) {
                    coroutineScope.switchBottomSheetVisibility(bottomSheetScaffoldState)
                }
            }
        }
    )
}

@Composable
private fun DimmedBackground(
    visible: Boolean,
    onClick: () -> Unit
) {
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
                ) {
                    onClick()
                }
        )
    }
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
