package com.example.financeapp.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.financeapp.ui.theme.MatuleTheme

@Composable
fun MinimalDialog(onDismissRequest: () -> Unit) {
    Dialog(onDismissRequest = {onDismissRequest()}) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            color = Color.White,
            modifier = Modifier
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Проверьте Ваш Email",
                    style = MatuleTheme.typography.headingBold24,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Мы отправили код восстановления пароля на вашу электронную почту.",
                    style = MatuleTheme.typography.subTitleRegular16.copy(color = MatuleTheme.colors.subTextDark),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}