package com.example.financeapp.ui.screens

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import com.example.financeapp.R
import com.example.financeapp.ui.theme.MatuleTheme

@SuppressLint("UnusedBoxWithConstraintsScope")
@Preview
@Composable
fun HomePage() {
    // Устанавливаем цвет системных баров
    val view = LocalView.current
    DisposableEffect(view) {
        val window = (view.context as Activity).window
        window.statusBarColor = 0xFF7AC793.toInt()
        window.navigationBarColor = 0xFF7AC793.toInt()

        // Для светлого текста в статус-баре (если нужен)
        WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false

        onDispose {}
    }

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(130.dp)
                    .background(color = Color(0xFF7AC793))
                    .statusBarsPadding().padding(top = 10.dp, start = 10.dp) // Учитываем отступ под статус-бар
            ) {
                IconButton(onClick = {}, modifier = Modifier.fillMaxHeight().width(40.dp)) {
                    Icon(
                        painter = painterResource(R.drawable.free_icon_sort_descending_8189398),
                        contentDescription = null,
                        modifier = Modifier.size(30.dp))
                }
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(150.dp)
                        .padding(end = 20.dp, start = 20.dp, top = 15.dp)
                ) {
                    Text(
                        text = "Spendly",
                        fontFamily = FontFamily(Font(R.font.aguafina_script)),
                        fontWeight = FontWeight.Bold,
                        fontSize = 21.sp,
                    )
                }
                IconButton(
                    onClick = {},
                    modifier = Modifier
                        .padding(horizontal = 18.dp)
                        .fillMaxHeight()
                        .width(40.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.loupe),
                        contentDescription = null,
                        modifier = Modifier.size(25.dp)
                    )
                }
                IconButton(
                    onClick = {},
                    modifier = Modifier
                        .padding(horizontal = 18.dp)
                        .fillMaxHeight()
                        .width(40.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.exchange),
                        contentDescription = null,
                        modifier = Modifier.size(25.dp)
                    )
                }
                IconButton(
                    onClick = {},
                    modifier = Modifier
                        .padding(horizontal = 18.dp)
                        .fillMaxHeight()
                        .width(40.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.dots),
                        contentDescription = null,
                        modifier = Modifier.size(25.dp)
                    )
                }
            }
        },
        content = { padding ->

        }

    )
}

