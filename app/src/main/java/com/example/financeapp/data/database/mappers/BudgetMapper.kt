package com.example.financeapp.data.database.mappers

import com.example.financeapp.data.database.entities.BudgetEntity
import com.example.financeapp.domain.models.Budget
import com.example.financeapp.domain.models.Category
import java.util.Date

object BudgetMapper {
    fun toDomain(entity: BudgetEntity, category: Category): Budget {
        return Budget(
            id = entity.id,
            category = category,
            amount = entity.amount,
            period = Budget.BudgetPeriod.valueOf(entity.period),
            startDate = Date(entity.startDate),
            endDate = entity.endDate?.let { Date(it) },
            spent = entity.spent
        )
    }

    fun toEntity(domain: Budget): BudgetEntity {
        return BudgetEntity(
            id = domain.id,
            categoryId = domain.category.id,
            amount = domain.amount,
            period = domain.period.name,
            startDate = domain.startDate.time,
            endDate = domain.endDate?.time,
            spent = domain.spent
        )
    }
}