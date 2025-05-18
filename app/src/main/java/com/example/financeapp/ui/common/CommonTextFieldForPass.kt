package com.example.financeapp.ui.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun TextFieldForPass(stringForLabel:String){
    var value by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val passRegex = Regex("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}\$")
    var trigger = false
    androidx.compose.material3.TextField(value = value,
        onValueChange = {
            if(passRegex.matches(it)){
                value = "*".repeat(it.count())
                password = it
                trigger = false
            }
            else{
                value = ""
                trigger = true
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .height(50.dp),
        label = { Text(stringForLabel) }
    )
}

