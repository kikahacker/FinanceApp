package com.example.financeapp.domain.repository

import com.example.financeapp.data.database.DAO.CategoryDao
import com.example.financeapp.data.database.DAO.TransactionDao
import com.example.financeapp.data.database.mappers.CategoryMapper
import com.example.financeapp.data.database.mappers.TransactionMapper
import com.example.financeapp.domain.models.Transaction
import javax.inject.Inject


class TransactionRepositoryImpl @Inject constructor(
    private val transactionDao: TransactionDao,
    private val categoryDao: CategoryDao
) : TransactionRepository {

    override suspend fun getAllTransactions(): List<Transaction> {
        return transactionDao.getAll().mapNotNull { entity ->
            categoryDao.getById(entity.categoryId)?.let { categoryEntity ->
                TransactionMapper.toDomain(entity, CategoryMapper.toDomain(categoryEntity))
            }
        }
    }

    override suspend fun getTransactionById(id: Long): Transaction? {
        return transactionDao.getById(id)?.let { entity ->
            categoryDao.getById(entity.categoryId)?.let { categoryEntity ->
                TransactionMapper.toDomain(entity, CategoryMapper.toDomain(categoryEntity))
            }
        }
    }

    override suspend fun getTransactionsByType(type: Transaction.TransactionType): List<Transaction> {
        return transactionDao.getByType(type.name).mapNotNull { entity ->
            categoryDao.getById(entity.categoryId)?.let { categoryEntity ->
                TransactionMapper.toDomain(entity, CategoryMapper.toDomain(categoryEntity))
            }
        }
    }

    override suspend fun getTransactionsByCategory(categoryId: Long): List<Transaction> {
        return transactionDao.getByCategory(categoryId).mapNotNull { entity ->
            categoryDao.getById(entity.categoryId)?.let { categoryEntity ->
                TransactionMapper.toDomain(entity, CategoryMapper.toDomain(categoryEntity))
            }
        }
    }

    override suspend fun getTransactionsByDateRange(start: Long, end: Long): List<Transaction> {
        return transactionDao.getByDateRange(start, end).mapNotNull { entity ->
            categoryDao.getById(entity.categoryId)?.let { categoryEntity ->
                TransactionMapper.toDomain(entity, CategoryMapper.toDomain(categoryEntity))
            }
        }
    }

    override suspend fun addTransaction(transaction: Transaction): Long {
        return transactionDao.insert(TransactionMapper.toEntity(transaction))
    }

    override suspend fun updateTransaction(transaction: Transaction) {
        transactionDao.update(TransactionMapper.toEntity(transaction))
    }

    override suspend fun deleteTransaction(transaction: Transaction) {
        transactionDao.delete(TransactionMapper.toEntity(transaction))
    }

    override suspend fun getTotalAmountByType(type: Transaction.TransactionType): Double {
        return transactionDao.getTotalAmountByType(type.name) ?: 0.0
    }
}