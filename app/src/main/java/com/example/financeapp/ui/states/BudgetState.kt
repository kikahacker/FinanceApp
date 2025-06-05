package com.example.financeapp.ui.states

import com.example.financeapp.domain.models.Budget

sealed class BudgetState {
    object Loading : BudgetState()
    data class Success(val budgets: List<Budget>) : BudgetState()
    data class Error(val message: String) : BudgetState()
}