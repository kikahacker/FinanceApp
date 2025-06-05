package com.example.financeapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financeapp.domain.models.Budget
import com.example.financeapp.domain.repository.BudgetRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class BudgetViewModel(
    private val budgetRepository: BudgetRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<BudgetUiState>(BudgetUiState.Loading)
    val uiState: StateFlow<BudgetUiState> = _uiState.asStateFlow()

    // Загружаем бюджеты при инициализации
    init {
        loadBudgets()
    }

    fun loadBudgets() {
        viewModelScope.launch {
            budgetRepository.getAllBudgets()
                .catch { e ->
                    _uiState.value = BudgetUiState.Error(e.message ?: "Failed to load budgets")
                }
                .collect { budgets ->
                    _uiState.value = BudgetUiState.Success(budgets)
                }
        }
    }

    fun addBudget(budget: Budget) {
        viewModelScope.launch {
            try {
                budgetRepository.insertBudget(budget)
                loadBudgets() // Обновляем список
            } catch (e: Exception) {
                _uiState.value = BudgetUiState.Error("Failed to add budget")
            }
        }
    }
    fun deleteBudget(budgetId: Long) {

        viewModelScope.launch {
            try {
                budgetRepository.deleteBudget(budgetId)
                loadBudgets()
            } catch (e: Exception) {
                _uiState.value = BudgetUiState.Error("Failed to delete budget")
            }
        }
    }
}

// Состояния UI для Budget
sealed class BudgetUiState {
    object Loading : BudgetUiState()
    data class Success(val budgets: List<Budget>) : BudgetUiState()
    data class Error(val message: String) : BudgetUiState()
}