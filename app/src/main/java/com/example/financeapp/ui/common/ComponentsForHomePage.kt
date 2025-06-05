package com.example.financeapp.ui.common

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.Modifier.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.financeapp.domain.models.Category
import com.example.financeapp.domain.models.CategoryStats
import com.example.financeapp.domain.models.PeriodType

@Composable
fun PeriodTab(
    type: PeriodType,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val text = when (type) {
        PeriodType.DAY -> "День"
        PeriodType.WEEK -> "Неделя"
        PeriodType.MONTH -> "Месяц"
        PeriodType.YEAR -> "Год"
    }

    Box(
        modifier = Modifier
            .fillMaxHeight()
            .clickable(onClick = onClick)
            .background(if (isSelected) Color.LightGray else Color.Transparent)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = text)
    }
}

@Composable
fun CategoryPieChart(
    categories: List<CategoryStats>,
    onCategoryClick: (Category) -> Unit
) {
    val total = categories.sumOf { it.amount }

    Canvas(modifier = Modifier
        .size(200.dp)
        .padding(16.dp)
        .clickable { /* Обработка клика */ }
    ) {
        var startAngle = 0f

        categories.forEach { category ->
            val sweepAngle = (category.amount / total * 360).toFloat()
            drawArc(
                color = category.category.color,
                startAngle = startAngle,
                sweepAngle = sweepAngle,
                useCenter = true
            )
            startAngle += sweepAngle
        }
    }

    // Легенда категорий
    Column {
        categories.forEach { category ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(4.dp)
                    .clickable { onCategoryClick(category.category) }
            ) {
                Box(
                    modifier = Modifier
                        .size(16.dp)
                        .background(category.category.color)
                )
                Text(
                    text = "${category.category.name} - ${category.percentage}%",
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}