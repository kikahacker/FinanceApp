package com.example.financeapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.ui.unit.dp
import com.example.financeapp.domain.models.Category
import com.example.financeapp.ui.viewmodels.PeriodViewModel

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun HomePage(viewModel: PeriodViewModel,
             onAddExpenseClick: () -> Unit,
             onCategoryClick: (Category) -> Unit
) {
    val uiState by viewModel.uiState
    val pagerState = rememberPagerState()

    LaunchedEffect(pagerState.currentPage) {
        viewModel.onPageChanged(pagerState.currentPage)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        // Переключатель периодов
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) { page ->
            val type = PeriodType.values()[page]
            PeriodTab(
                type = type,
                isSelected = uiState.type == type,
                onClick = { /* Можно добавить обработчик */ }
            )
        }

        // Индикатор страниц
        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(8.dp)
        )

        // Основной контент
        if (uiState.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        } else {
            PeriodContent(
                state = uiState,
                onAddExpenseClick = onAddExpenseClick,
                onCategoryClick = onCategoryClick
            )
        }
    }
}

@Composable
private fun PeriodContent(
    state: PeriodUiState,
    onAddExpenseClick: () -> Unit,
    onCategoryClick: (CategoryModel) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Заголовок с датой
        Text(
            text = state.title,
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Баланс
        BalanceCard(balance = state.balance)

        // Доходы/расходы
        IncomeExpenseRow(income = state.income, expense = state.expense)

        // Круговые диаграммы категорий
        CategoryPieChart(
            categories = state.categories,
            onCategoryClick = onCategoryClick
        )

        // Кнопка добавления
        FloatingActionButton(
            onClick = onAddExpenseClick,
            modifier = Modifier
                .align(Alignment.End)
                .padding(16.dp)
        ) {
            Icon(Icons.Default.Add, contentDescription = "Добавить")
        }
    }
}

