package com.example.financeapp.ui.common

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.financeapp.ui.theme.MatuleTheme


@Composable
fun TextFieldForEmail(stringForLabel:String){
    var value by remember { mutableStateOf("") }
    val emailRegex = Regex("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}\$")
    var isFocused by remember { mutableStateOf(false) }
    val borderColor = when {
        emailRegex.matches(value) -> Color.Green
        isFocused ->Color.Blue
        else -> Color.Red
    }
    TextField(value = value,
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
        )
    )
    if (value.isNotEmpty() && !emailRegex.matches(value)) {
        Text(
            text = "Некорректный email",
            color = Color.Red,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(start = 20.dp, top = 5.dp)
        )
    }
}
@Preview
@Composable
fun poxyi(){
    MatuleTheme {
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            TextFieldForEmail("123")
        }
    }
}