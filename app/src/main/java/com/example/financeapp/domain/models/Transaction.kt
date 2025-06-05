package com.example.financeapp.domain.models

import java.sql.Date

data class Transaction(
    val id: Long = 0,
    val amount: Double,
    val date: Date,
    val note: String?,
    val category: Category,
    val type: TransactionType
) {
    enum class TransactionType { INCOME, EXPENSE }
}