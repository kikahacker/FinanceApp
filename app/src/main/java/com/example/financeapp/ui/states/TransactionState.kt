package com.example.financeapp.ui.states

import com.example.financeapp.domain.models.Transaction

sealed class TransactionState {
    object Loading : TransactionState()
    data class Success(val transactions: List<Transaction>) : TransactionState()
    data class Error(val message: String) : TransactionState()
}