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
fun TextFieldForEmail(stringForLabel:String){
    var value by remember { mutableStateOf("") }
    val emailRegex = Regex("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}\$")
    var triger = false
    androidx.compose.material3.TextField(value = value,
        onValueChange = {
            if(emailRegex.matches(it)){
                value = it
                triger = false
            }
            else{
                value = ""
                triger = true
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .height(50.dp),
        label = { Text(stringForLabel) }
    )
}