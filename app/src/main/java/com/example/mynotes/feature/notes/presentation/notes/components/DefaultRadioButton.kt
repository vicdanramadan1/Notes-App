package com.example.mynotes.featurenote.presentation.notes.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun DefaultRadioButton(
    text: String,
    selected: Boolean,
    onClick: ()-> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selected,
            onClick = onClick,
            colors = RadioButtonDefaults.colors(
                selectedColor = MaterialTheme.colorScheme.primary,
                unselectedColor = MaterialTheme.colorScheme.onBackground
            )
        )

        Text(text = text , style = MaterialTheme.typography.bodySmall)
    }
}

@Preview
@Composable
fun DefaultRadioButtonPreview() {
    DefaultRadioButton(text = "Selected", selected = true, onClick = { /*TODO*/ })
}