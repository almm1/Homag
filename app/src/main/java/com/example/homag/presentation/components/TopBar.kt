package com.example.homag.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController
import com.example.homag.R
import com.example.homag.presentation.ui.theme.Typography
import com.example.homag.presentation.ui.theme.secondary

@Composable
 fun TopBar(navController: NavHostController) {

    Row(
        verticalAlignment = Alignment.CenterVertically,

        modifier = Modifier.clickable { navController.navigateUp() }) {
        if (navController.previousBackStackEntry != null) {
            Icon(
                modifier = Modifier
                    .clip(MaterialTheme.shapes.small),
                painter = painterResource(id = R.drawable.ic_arrow_back),
                contentDescription = "Back"
            )
            Text(
                text = stringResource(R.string.back),
                textAlign = TextAlign.Start,
                style = Typography.h4,
                color = secondary
            )
        } else {
            null
        }
    }
}