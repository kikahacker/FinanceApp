package com.example.financeapp.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import com.example.financeapp.domain.models.Category
import com.example.financeapp.domain.models.Transaction

@Entity(tableName = "categories")
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    @ColumnInfo(name = "icon_res") val iconRes: String,
    val type: String, // "INCOME" или "EXPENSE"
    val color: String
)