package com.example.homag.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.homag.R
import com.example.homag.presentation.ui.theme.Typography

@Composable
 fun Title() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(top = 64.dp)
    ) {
        Icon(Icons.Filled.PlayArrow, contentDescription = "")
        Text(
            text = stringResource(R.string.homag),
            style = Typography.h1
        )
    }
}