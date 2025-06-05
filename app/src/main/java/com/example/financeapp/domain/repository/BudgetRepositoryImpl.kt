package com.example.financeapp.domain.repository

import com.example.financeapp.data.database.DAO.BudgetDao
import com.example.financeapp.data.database.DAO.CategoryDao
import com.example.financeapp.data.database.mappers.BudgetMapper
import com.example.financeapp.data.database.mappers.CategoryMapper
import com.example.financeapp.domain.models.Budget
import javax.inject.Inject

class BudgetRepositoryImpl @Inject constructor(
    private val budgetDao: BudgetDao,
    private val categoryDao: CategoryDao
) : BudgetRepository {

    override suspend fun getAllBudgets(): List<Budget> {
        return budgetDao.getAll().mapNotNull { entity ->
            categoryDao.getById(entity.categoryId)?.let { categoryEntity ->
                BudgetMapper.toDomain(entity, CategoryMapper.toDomain(categoryEntity))
            }
        }
    }

    override suspend fun getBudgetById(id: Long): Budget? {
        return budgetDao.getById(id)?.let { entity ->
            categoryDao.getById(entity.categoryId)?.let { categoryEntity ->
                BudgetMapper.toDomain(entity, CategoryMapper.toDomain(categoryEntity))
            }
        }
    }

    override suspend fun getBudgetsByPeriod(period: Budget.BudgetPeriod): List<Budget> {
        return budgetDao.getByPeriod(period.name).mapNotNull { entity ->
            categoryDao.getById(entity.categoryId)?.let { categoryEntity ->
                BudgetMapper.toDomain(entity, CategoryMapper.toDomain(categoryEntity))
            }
        }
    }

    override suspend fun addBudget(budget: Budget): Long {
        return budgetDao.insert(BudgetMapper.toEntity(budget))
    }

    override suspend fun updateBudget(budget: Budget) {
        budgetDao.update(BudgetMapper.toEntity(budget))
    }

    override suspend fun deleteBudget(budget: Budget) {
        budgetDao.delete(BudgetMapper.toEntity(budget))
    }

    override suspend fun getActiveBudgets(currentDate: Long): List<Budget> {
        return budgetDao.getActiveBudgets(currentDate).mapNotNull { entity ->
            categoryDao.getById(entity.categoryId)?.let { categoryEntity ->
                BudgetMapper.toDomain(entity, CategoryMapper.toDomain(categoryEntity))
            }
        }
    }
}