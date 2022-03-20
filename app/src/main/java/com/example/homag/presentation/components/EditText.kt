package com.example.homag.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.homag.R
import com.example.homag.presentation.ui.theme.Typography
import com.example.homag.presentation.ui.theme.semiWhiteTextColor
import com.example.homag.presentation.ui.theme.textFieldBackgroundColor

@Composable
fun editText(
    label: String,
    title: String = label,
    options: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    isPassword: Boolean = false,
    length: Int = 255
): String {
    var text: String by rememberSaveable { mutableStateOf("") }
    Text(title, style = Typography.h3)
    Margin(4)
    OutlinedTextField(
        trailingIcon = {
            if (isPassword) TextButton(onClick = {}, Modifier.padding(end = 8.dp)) {
                Text(
                    text = stringResource(R.string.remind_password),
                    style = Typography.h3
                )
            }
        },
        singleLine = true,
        placeholder = { Text(text = label, style = Typography.h4) },
        keyboardOptions = options,
        visualTransformation = visualTransformation,
        value = text,
        onValueChange = {
            if (it.length <= length) {
                text = it
            }
        },
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .background(textFieldBackgroundColor, shape = RoundedCornerShape(8.dp)),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = Color.Transparent,
            focusedBorderColor = semiWhiteTextColor,
            backgroundColor = Color.Black, cursorColor = semiWhiteTextColor
        )
    )
    Margin(20)
    return visualTransformation.filter(AnnotatedString(text)).text.text
}