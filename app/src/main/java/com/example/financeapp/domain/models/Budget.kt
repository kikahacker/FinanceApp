package com.example.financeapp.domain.models

import java.util.Date

data class Budget(
    val id: Long = 0,
    val category: Category,
    val amount: Double,
    val period: BudgetPeriod,
    val startDate: Date,
    val endDate: Date?,
    val spent: Double = 0.0
) {
    enum class BudgetPeriod { DAILY, WEEKLY, MONTHLY, YEARLY }

    val progress: Float get() = (spent.toFloat() / amount.toFloat()).coerceIn(0f, 1f)
    val isOverBudget: Boolean get() = spent > amount
}