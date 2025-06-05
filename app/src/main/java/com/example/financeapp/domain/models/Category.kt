package com.example.financeapp.domain.models

import androidx.compose.ui.graphics.Color

data class Category(
    val id: Long = 0,
    val name: String,
    val iconRes: String,
    val type: Transaction.TransactionType,
    val color: Color
)