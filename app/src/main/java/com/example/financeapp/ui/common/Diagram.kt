package com.example.financeapp.ui.common


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.financeapp.R
import com.example.financeapp.ui.theme.MatuleTheme
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun CircularDiagram(
    centerText1: String,
    centerText2: String,
    buttonsCount: Int,
    radius: Dp = 150.dp,
    onButtonClick: (Int) -> Unit
) {
    val angleStep = 360f / buttonsCount

    Box(
        modifier = Modifier
            .size(radius * 2)
            .background(
                color = Color.LightGray.copy(alpha = 0.2f),
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = centerText1,
                style = MatuleTheme.typography.subTitleRegular16,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = centerText2,
                style = MatuleTheme.typography.subTitleRegular16,
                color = Color.Gray
            )
        }

        Box(modifier = Modifier.fillMaxSize()) {
            repeat(buttonsCount) { index ->
                val angle = angleStep * index
                val x = cos(Math.toRadians(angle.toDouble())).toFloat()
                val y = sin(Math.toRadians(angle.toDouble())).toFloat()

                IconButton(
                    onClick = { onButtonClick(index) },
                    modifier = Modifier
                        .offset(
                            x = (radius * x),
                            y = (radius * y)
                        )
                        .size(40.dp)
                        .align(Alignment.Center),
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = Color.White
                    )
                ) {
                    Icon(painter = painterResource(id = getIconForIndex(index)), // Замените на вашу иконку
                        contentDescription = "Кнопка ${index + 1}",
                        modifier = Modifier.size(24.dp))
                }
            }
        }
    }
}

// Функция для получения разных иконок для каждой кнопки
fun getIconForIndex(index: Int): Int {
    return when (index % 5) {
        0 -> R.drawable.free_icon_car_5670285
        1 -> R.drawable.free_icon_gift_116392
        2 -> R.drawable.free_icon_taxi_5900567
        3 -> R.drawable.free_icon_tram_4332345
        else -> R.drawable.free_icon_cocktail_259953
    }
}