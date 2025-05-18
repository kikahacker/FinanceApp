package com.example.financeapp.ui.common

import android.widget.Button
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun CommonButton(onClick: ()-> Unit,

                 content: @Composable () -> Unit){
    Button(onClick = onClick,
        modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp).height(50.dp), shape = RoundedCornerShape(15.dp)) {
        content()
    }
}