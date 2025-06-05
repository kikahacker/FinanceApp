package com.example.financeapp.domain.models

enum class PeriodType { DAY, WEEK, MONTH, YEAR }

data class PeriodUiState(
    val type: PeriodType,
    val title: String,
    val balance: Double,
    val income: Double,
    val expense: Double,
    val categories: List<CategoryStats>,
    val isLoading: Boolean = false
)

data class CategoryStats(
    val category: Category,
    val amount: Double,
    val percentage: Int
)