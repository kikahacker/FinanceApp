package com.example.financeapp.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import com.example.financeapp.domain.models.Category
import com.example.financeapp.domain.models.Transaction
import java.sql.Date

@Entity(tableName = "transactions")
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val amount: Double,
    val date: Long, // Храним как timestamp
    val note: String?,
    @ColumnInfo(name = "category_id") val categoryId: Long,
    val type: String // "INCOME" или "EXPENSE"
)