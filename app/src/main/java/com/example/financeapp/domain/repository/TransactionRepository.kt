package com.example.financeapp.domain.repository

import com.example.financeapp.domain.models.Transaction
import com.example.financeapp.domain.models.Transaction.TransactionType
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {
    suspend fun insertTransaction(transaction: Transaction)
    suspend fun getAllTransactions(): Flow<List<Transaction>>
    suspend fun getTransactionById(id: Long): Transaction?
    suspend fun getTransactionsByType(type: TransactionType): List<Transaction>
    suspend fun getTransactionsByCategory(categoryId: Long): List<Transaction>
    suspend fun getTransactionsByDateRange(start: Long, end: Long): List<Transaction>
    suspend fun addTransaction(transaction: Transaction): Long
    suspend fun updateTransaction(transaction: Transaction)
    suspend fun deleteTransaction(transaction: Transaction)
    suspend fun getTotalAmountByType(type: TransactionType): Double
}