package com.example.financeapp.ui.viewmodels

import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financeapp.domain.models.PeriodType
import com.example.financeapp.domain.models.PeriodUiState
import com.example.financeapp.domain.repository.TransactionRepository
import kotlinx.coroutines.launch
import java.time.LocalDate

class PeriodViewModel(
    private val transactionRepository: TransactionRepository
) : ViewModel() {

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

    private val pagerState = PagerState(
        initialPage = PeriodType.values().indexOf(PeriodType.DAY),
        pageCount = { PeriodType.values().size }
    )

    init {
        loadData(PeriodType.DAY)
    }

    fun onPageChanged(index: Int) {
        val newType = PeriodType.values()[index]
        loadData(newType)
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
        return calculatePeriodData(
            type = PeriodType.DAY,
            title = date.format("EEEE, d MMMM", Locale("ru")),
            transactions = transactions
        )
    }

    // Остальные методы загрузки...
}