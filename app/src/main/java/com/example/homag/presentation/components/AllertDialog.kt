package com.example.homag.presentation.components

import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.homag.R
import com.example.homag.presentation.MainViewModel
import com.example.homag.presentation.ui.theme.Typography
import com.example.homag.utils.FALSE
import com.example.homag.utils.TRUE

@Composable
fun AlertDialog(
    show: Boolean,
    onConfirm: () -> Unit,
    message: String,
    viewModel: MainViewModel,
    navController: NavHostController
) {
    if (show) {
        androidx.compose.material.AlertDialog(
            onDismissRequest = {},
            confirmButton = {
                TextButton(onClick = {
                    if (viewModel.authStatus.value || viewModel.regStatus.value == TRUE) {
                        viewModel.authStatus.value = false
                        viewModel.regStatus.value = FALSE
                        navController.navigateUp()
                    }
                    onConfirm()
                })
                { Text(text = stringResource(R.string.ok), style = Typography.h4, color = Color.Black) }
            },
            text = { Text(text = message, style = Typography.body1, color = Color.Black) }
        )
    }
}