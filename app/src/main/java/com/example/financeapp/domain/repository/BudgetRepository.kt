package com.example.financeapp.domain.repository


import com.example.financeapp.domain.models.Budget
import kotlinx.coroutines.flow.Flow

interface BudgetRepository {
    suspend fun insertBudget(budget: Budget)
    suspend fun getAllBudgets(): Flow<List<Budget>>
    suspend fun getBudgetById(id: Long): Budget?
    suspend fun getBudgetsByPeriod(period: Budget.BudgetPeriod): List<Budget>
    suspend fun addBudget(budget: Budget): Long
    suspend fun updateBudget(budget: Budget)
    suspend fun deleteBudget(budgetId: Long)
    suspend fun getActiveBudgets(currentDate: Long): List<Budget>
}