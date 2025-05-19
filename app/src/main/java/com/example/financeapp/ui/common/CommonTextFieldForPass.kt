package com.example.financeapp.ui.common

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp


@Composable
fun TextFieldForPass(stringForLabel:String){
    var value by remember { mutableStateOf("") }
    val passRegex = Regex("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}\$")
    var isFocused by remember { mutableStateOf(false) }
    val borderColor = when {
        passRegex.matches(value) -> Color.Green
        isFocused -> Color.Blue
        else -> Color.Red
    }
    androidx.compose.material3.TextField(value = value,
        onValueChange = {
            value = it
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .height(50.dp)
            .border(width = 2.dp,color = borderColor, shape = RoundedCornerShape(10.dp))
            .onFocusChanged { isFocused = it.isFocused },
        label = { Text(stringForLabel) },
        maxLines = 1,
        shape = RoundedCornerShape(10.dp),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        visualTransformation = PasswordVisualTransformation(),
    )
    if (value.isNotEmpty() && !passRegex.matches(value)) {
        Text(
            text = "Некорректный пароль",
            color = Color.Red,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(start = 20.dp, top = 5.dp)
        )
    }
}

