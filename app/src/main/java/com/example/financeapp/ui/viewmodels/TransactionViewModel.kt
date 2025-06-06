package com.example.financeapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financeapp.domain.models.Category
import com.example.financeapp.domain.models.Transaction
import com.example.financeapp.domain.repository.CategoryRepository
import com.example.financeapp.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class TransactionViewModel(
    private val transactionRepository: TransactionRepository,
    private val categoryRepository: CategoryRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<TransactionUiState>(TransactionUiState.Loading)
    val uiState: StateFlow<TransactionUiState> = _uiState.asStateFlow()

    private val _categories = MutableStateFlow<List<Category>>(emptyList())
    val categories: StateFlow<List<Category>> = _categories

    init {
        loadTransactions()
        loadCategories()
    }

    fun loadTransactions() {
        viewModelScope.launch {
            transactionRepository.getAllTransactions().asFlow()
                .catch { e ->
                    _uiState.value = TransactionUiState.Error(e.message ?: "Failed to load transactions")
                }
                .collect { transactions ->
                    _uiState.value = TransactionUiState.Success(transactions)
                }
        }
    }

    fun loadCategories() {
        viewModelScope.launch {
            categoryRepository.getAllCategories()
                .collect { categories ->
                    _categories.value = categories
                }
        }
    }

    fun addTransaction(transaction: Transaction) {
        viewModelScope.launch {
            try {
                transactionRepository.insertTransaction(transaction)
                loadTransactions()
            } catch (e: Exception) {
                _uiState.value = TransactionUiState.Error("Failed to add transaction")
            }
        }
    }

    fun filterByCategory(categoryId: Long) {
        viewModelScope.launch {
            transactionRepository.getTransactionsByCategory(categoryId).asFlow()
                .collect { transactions ->
                    _uiState.value = TransactionUiState.Success(transactions)
                }
        }
    }
}

// Состояния UI для Transaction
sealed class TransactionUiState {
    object Loading : TransactionUiState()
    data class Success(val transactions: Transaction) : TransactionUiState()
    data class Error(val message: String) : TransactionUiState()
}