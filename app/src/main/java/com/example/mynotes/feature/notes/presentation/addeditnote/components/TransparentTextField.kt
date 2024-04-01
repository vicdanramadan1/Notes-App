package com.example.mynotes.featurenote.presentation.addeditnote.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransparentTextField(
    text: String,
    modifier: Modifier = Modifier,
    hint: String? = null,
    textStyle: TextStyle = LocalTextStyle.current,
    singleLine: Boolean = false,
    onValueChange: (String) -> Unit,
    onFocusChange: (FocusState) -> Unit
) {
    TextField(
        value = text,
        onValueChange = onValueChange,
        textStyle = textStyle,
        singleLine = singleLine,
        modifier = modifier
            .fillMaxWidth()
            .onFocusChanged {
                onFocusChange(it)
            },
        placeholder = {
            if (hint != null && text.isBlank())
                Text(
                    text = hint ,
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                    color = Color.Black
                )
        },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent

        )
    )
}

@Preview
@Composable
fun TransparentTextFieldPreview() {
    TransparentTextField(text = "", onValueChange = {}, onFocusChange = {}, hint = "hint")
}