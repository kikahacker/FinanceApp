package com.example.financeapp.domain.repository

import com.example.financeapp.domain.models.Category
import com.example.financeapp.domain.models.Transaction.TransactionType
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    suspend fun insertCategory(category: Category)
    suspend fun getAllCategories(): Flow<List<Category>>
    suspend fun getCategoryById(id: Long): Category?
    suspend fun getCategoriesByType(type: TransactionType): List<Category>
    suspend fun addCategory(category: Category): Long
    suspend fun updateCategory(category: Category)
    suspend fun deleteCategory(categoryId: Long)
    suspend fun seedDefaultCategories()
}