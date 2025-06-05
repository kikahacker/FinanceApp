package com.example.financeapp.domain.repository


import com.example.financeapp.domain.models.Budget

interface BudgetRepository {
    suspend fun getAllBudgets(): List<Budget>
    suspend fun getBudgetById(id: Long): Budget?
    suspend fun getBudgetsByPeriod(period: Budget.BudgetPeriod): List<Budget>
    suspend fun addBudget(budget: Budget): Long
    suspend fun updateBudget(budget: Budget)
    suspend fun deleteBudget(budget: Budget)
    suspend fun getActiveBudgets(currentDate: Long): List<Budget>
}