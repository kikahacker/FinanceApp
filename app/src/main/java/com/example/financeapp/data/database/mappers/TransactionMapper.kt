package com.example.financeapp.data.database.mappers

import com.example.financeapp.data.database.entities.TransactionEntity
import com.example.financeapp.domain.models.Category
import com.example.financeapp.domain.models.Transaction
import java.sql.Date

object TransactionMapper {
    fun toDomain(entity: TransactionEntity, category: Category): Transaction {
        return Transaction(
            id = entity.id,
            amount = entity.amount,
            date = Date(entity.date),
            note = entity.note,
            category = category,
            type = Transaction.TransactionType.valueOf(entity.type)
        )
    }

    fun toEntity(domain: Transaction): TransactionEntity {
        return TransactionEntity(
            id = domain.id,
            amount = domain.amount,
            date = domain.date.time,
            note = domain.note,
            categoryId = domain.category.id,
            type = domain.type.name
        )
    }
}