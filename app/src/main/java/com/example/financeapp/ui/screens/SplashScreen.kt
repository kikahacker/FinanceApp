package com.example.financeapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.datastore.core.DataStore
import com.example.financeapp.R
import com.example.financeapp.ui.data.local.DataStoreOnBoarding
import com.example.financeapp.ui.theme.MatuleTheme

@Preview

@Composable
fun SplashScreen(){
    Column(modifier = Modifier.fillMaxSize().background(Color(0xFF4169e1)), verticalArrangement = Arrangement.Center) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .height(500.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                modifier = Modifier.size(180.dp)
                    .background(MatuleTheme.colors.Circle.copy(alpha = 0.6f), shape = RoundedCornerShape(100)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.fabula),
                    contentDescription = null,
                    modifier = Modifier.size(150.dp)
                )
            }
            Text(text = "SPENDLY", style = MatuleTheme
                .typography
                .headingBold32,
                color = Color.White,
                modifier = Modifier.padding(top = 50.dp))
            Text(text = "EASY CONTROL YOUR FINANCE STATE", style = MatuleTheme
                .typography
                .subTitleRegular20,
                color = Color.White,
                modifier = Modifier.padding(top = 50.dp),
                textAlign = TextAlign.Center)
        }
    }
}