package com.example.financeapp.ui.common


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RoundBorderButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    borderColor: Color = Color.Blue, // Цвет границы
    textColor: Color = Color.Black,  // Цвет текста
    fontSize: TextUnit = 16.sp,
    borderWidth: Dp = 5.dp,         // Толщина обводки
    buttonSize: Dp = 130.dp         // Размер кнопки
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(buttonSize)
            .clip(CircleShape)
            .border(
                width = borderWidth,
                color = borderColor,
                shape = CircleShape
            )
            .background(Color.White) // Белый фон внутри
            .clickable(onClick = onClick)
    ) {
        Text(
            text = text,
            color = textColor,
            fontSize = fontSize,
            textAlign = TextAlign.Center
        )
    }
}