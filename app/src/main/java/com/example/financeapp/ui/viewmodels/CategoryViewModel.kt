package com.example.financeapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financeapp.domain.models.Category
import com.example.financeapp.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class CategoryViewModel(
    private val categoryRepository: CategoryRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<CategoryUiState>(CategoryUiState.Loading)
    val uiState: StateFlow<CategoryUiState> = _uiState.asStateFlow()

    init {
        loadCategories()
    }

    fun loadCategories() {
        viewModelScope.launch {
            categoryRepository.getAllCategories()
                .catch { e ->
                    _uiState.value = CategoryUiState.Error(e.message ?: "Failed to load categories")
                }
                .collect { categories ->
                    _uiState.value = CategoryUiState.Success(categories)
                }
        }
    }

    fun addCategory(category: Category) {
        viewModelScope.launch {
            try {
                categoryRepository.insertCategory(category)
                loadCategories()
            } catch (e: Exception) {
                _uiState.value = CategoryUiState.Error("Failed to add category")
            }
        }
    }

    fun deleteCategory(categoryId: Long) {
        viewModelScope.launch {
            try {
                categoryRepository.deleteCategory(categoryId)
                loadCategories()
            } catch (e: Exception) {
                _uiState.value = CategoryUiState.Error("Failed to delete category")
            }
        }
    }
}

// Состояния UI для Category
sealed class CategoryUiState {
    object Loading : CategoryUiState()
    data class Success(val categories: List<Category>) : CategoryUiState()
    data class Error(val message: String) : CategoryUiState()
}