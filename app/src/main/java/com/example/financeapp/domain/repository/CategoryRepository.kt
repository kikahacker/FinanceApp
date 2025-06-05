package com.example.financeapp.domain.repository

import com.example.financeapp.domain.models.Category
import com.example.financeapp.domain.models.Transaction.TransactionType

interface CategoryRepository {
    suspend fun getAllCategories(): List<Category>
    suspend fun getCategoryById(id: Long): Category?
    suspend fun getCategoriesByType(type: TransactionType): List<Category>
    suspend fun addCategory(category: Category): Long
    suspend fun updateCategory(category: Category)
    suspend fun deleteCategory(category: Category)
    suspend fun seedDefaultCategories()
}