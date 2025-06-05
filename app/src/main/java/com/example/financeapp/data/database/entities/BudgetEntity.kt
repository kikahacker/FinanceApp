package com.example.financeapp.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import com.example.financeapp.domain.models.Budget
import com.example.financeapp.domain.models.Category
import java.sql.Date

@Entity(tableName = "budgets")
data class BudgetEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "category_id") val categoryId: Long,
    val amount: Double,
    val period: String, // "DAILY", "WEEKLY" и т.д.
    @ColumnInfo(name = "start_date") val startDate: Long,
    @ColumnInfo(name = "end_date") val endDate: Long?,
    val spent: Double = 0.0
)