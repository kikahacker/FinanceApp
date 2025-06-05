package com.example.financeapp.domain.repository

import com.example.financeapp.data.database.DAO.CategoryDao
import com.example.financeapp.data.database.mappers.CategoryMapper
import com.example.financeapp.domain.models.Category
import com.example.financeapp.domain.models.Transaction.TransactionType
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val categoryDao: CategoryDao
) : CategoryRepository {

    override suspend fun getAllCategories(): List<Category> {
        return categoryDao.getAll().map { CategoryMapper.toDomain(it) }
    }

    override suspend fun getCategoryById(id: Long): Category? {
        return categoryDao.getById(id)?.let { CategoryMapper.toDomain(it) }
    }

    override suspend fun getCategoriesByType(type: TransactionType): List<Category> {
        return categoryDao.getByType(type.name).map { CategoryMapper.toDomain(it) }
    }

    override suspend fun addCategory(category: Category): Long {
        return categoryDao.insert(CategoryMapper.toEntity(category))
    }

    override suspend fun updateCategory(category: Category) {
        categoryDao.update(CategoryMapper.toEntity(category))
    }

    override suspend fun deleteCategory(category: Category) {
        categoryDao.delete(CategoryMapper.toEntity(category))
    }

    override suspend fun seedDefaultCategories() {
        if (categoryDao.getCount() == 0) {
            val defaultCategories = listOf(
                // Доходы
                Category(
                    name = "Зарплата",
                    iconRes = "ic_salary",
                    type = TransactionType.INCOME,
                    color = "#4CAF50"
                ),
                // Расходы
                Category(
                    name = "Продукты",
                    iconRes = "ic_groceries",
                    type = TransactionType.EXPENSE,
                    color = "#FF5722"
                ),
                // ... другие категории
            )
            defaultCategories.forEach { category ->
                categoryDao.insert(CategoryMapper.toEntity(category))
            }
        }
    }
}