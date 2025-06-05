package com.example.financeapp.domain.models

data class Category(
    val id: Long = 0,
    val name: String,
    val iconRes: String,
    val type: Transaction.TransactionType,
    val color: String
)