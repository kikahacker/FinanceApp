package com.example.financeapp.ui.viewmodels

import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financeapp.domain.models.Category
import com.example.financeapp.domain.models.CategoryStats
import com.example.financeapp.domain.models.PeriodType
import com.example.financeapp.domain.models.PeriodUiState
import com.example.financeapp.domain.models.Transaction
import com.example.financeapp.domain.repository.CategoryRepository
import com.example.financeapp.domain.repository.TransactionRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters
import java.util.Locale

class PeriodViewModel(
    private val transactionRepository: TransactionRepository,
    private val categoryRepository: CategoryRepository
) : ViewModel() {

    // Состояние пагинации периодов
    val pagerState = PagerState(
        initialPage = PeriodType.DAY.ordinal,
        initialPageOffsetFraction = 0f
    ) {
        PeriodType.values().size
    }

    // UI состояние
    private val _uiState = mutableStateOf(
        PeriodUiState(
        type = PeriodType.DAY,
        title = "",
        balance = 0.0,
        income = 0.0,
        expense = 0.0,
        categories = emptyList(),
        isLoading = true
    )
    )
    val uiState: State<PeriodUiState> = _uiState

    init {
        // Загрузка начальных данных
        loadCurrentPeriodData()

        // Отслеживание смены периода
        viewModelScope.launch {
            pagerState.interactionSource.interactions
                .collect {
                    if (pagerState.currentPage != _uiState.value.type.ordinal) {
                        loadData(PeriodType.values()[pagerState.currentPage])
                    }
                }
        }
    }

    fun loadCurrentPeriodData() {
        loadData(PeriodType.values()[pagerState.currentPage])
    }

    private fun loadData(type: PeriodType) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            val data = when (type) {
                PeriodType.DAY -> loadDayData()
                PeriodType.WEEK -> loadWeekData()
                PeriodType.MONTH -> loadMonthData()
                PeriodType.YEAR -> loadYearData()
            }

            _uiState.value = data.copy(isLoading = false)
        }
    }

    private suspend fun loadDayData(): PeriodUiState {
        val date = LocalDate.now()
        val transactions = transactionRepository.getByDate(date)
        val categories = categoryRepository.getAllCategories().first()

        val formatter = DateTimeFormatter.ofPattern("EEEE, d MMMM", Locale("ru"))

        return PeriodUiState(
            type = PeriodType.DAY,
            title = date.format(formatter),
            balance = calculateBalance(transactions),
            income = calculateIncome(transactions),
            expense = calculateExpense(transactions),
            categories = calculateCategoryStats(transactions, categories),
            isLoading = false
        )
    }

    private suspend fun loadWeekData(): PeriodUiState {
        val startDate = LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))
        val endDate = startDate.plusDays(6)
        val transactions = transactionRepository.getByDateRange(startDate, endDate)
        val categories = categoryRepository.getAllCategories().first()

        val formatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale("ru"))
        val title = "${startDate.format(formatter)} - ${endDate.format(formatter)}"

        return PeriodUiState(
            type = PeriodType.WEEK,
            title = title,
            balance = calculateBalance(transactions),
            income = calculateIncome(transactions),
            expense = calculateExpense(transactions),
            categories = calculateCategoryStats(transactions, categories),
            isLoading = false
        )
    }

    private suspend fun loadMonthData(): PeriodUiState {
        val date = LocalDate.now()
        val startDate = date.withDayOfMonth(1)
        val endDate = date.withDayOfMonth(date.lengthOfMonth())
        val transactions = transactionRepository.getByDateRange(startDate, endDate)
        val categories = categoryRepository.getAllCategories().first()

        val formatter = DateTimeFormatter.ofPattern("MMMM yyyy", Locale("ru"))

        return PeriodUiState(
            type = PeriodType.MONTH,
            title = date.format(formatter),
            balance = calculateBalance(transactions),
            income = calculateIncome(transactions),
            expense = calculateExpense(transactions),
            categories = calculateCategoryStats(transactions, categories),
            isLoading = false
        )
    }

    private suspend fun loadYearData(): PeriodUiState {
        val year = LocalDate.now().year
        val startDate = LocalDate.of(year, 1, 1)
        val endDate = LocalDate.of(year, 12, 31)
        val transactions = transactionRepository.getByDateRange(startDate, endDate)
        val categories = categoryRepository.getAllCategories().first()

        return PeriodUiState(
            type = PeriodType.YEAR,
            title = year.toString(),
            balance = calculateBalance(transactions),
            income = calculateIncome(transactions),
            expense = calculateExpense(transactions),
            categories = calculateCategoryStats(transactions, categories),
            isLoading = false
        )
    }

    private fun calculateBalance(transactions: List<Transaction>): Double {
        return transactions.sumOf { if (it.isIncome) it.amount else -it.amount }
    }

    private fun calculateIncome(transactions: List<Transaction>): Double {
        return transactions.filter { it.isIncome }.sumOf { it.amount }
    }

    private fun calculateExpense(transactions: List<Transaction>): Double {
        return transactions.filterNot { it.isIncome }.sumOf { it.amount }
    }

    private fun calculateCategoryStats(
        transactions: List<Transaction>,
        allCategories: List<Category>
    ): List<CategoryStats> {
        val expenses = transactions.filterNot { it.isIncome }
        val totalExpense = expenses.sumOf { it.amount }

        if (totalExpense <= 0) return emptyList()

        return allCategories.map { category ->
            val amount = expenses
                .filter { it.category.id == category.id }
                .sumOf { it.amount }

            CategoryStats(
                category = category,
                amount = amount,
                percentage = (amount / totalExpense * 100).toInt()
            )
        }.filter { it.amount > 0 }
    }
}

