package com.example.financeapp.data.database.mappers

import com.example.financeapp.data.database.entities.CategoryEntity
import com.example.financeapp.domain.models.Category
import com.example.financeapp.domain.models.Transaction

object CategoryMapper {
    fun toDomain(entity: CategoryEntity): Category {
        return Category(
            id = entity.id,
            name = entity.name,
            iconRes = entity.iconRes,
            type = Transaction.TransactionType.valueOf(entity.type),
            color = entity.color
        )
    }

    fun toEntity(domain: Category): CategoryEntity {
        return CategoryEntity(
            id = domain.id,
            name = domain.name,
            iconRes = domain.iconRes,
            type = domain.type.name,
            color = domain.color
        )
    }
}