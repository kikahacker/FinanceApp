package com.example.financeapp.data.database.DAO

import androidx.room.*
import com.example.financeapp.data.database.entities.BudgetEntity


@Dao
interface BudgetDao {
    @Insert
    suspend fun insert(budget: BudgetEntity): Long

    @Update
    suspend fun update(budget: BudgetEntity)

    @Delete
    suspend fun delete(budget: BudgetEntity)

    @Query("SELECT * FROM budgets")
    suspend fun getAll(): List<BudgetEntity>

    @Query("SELECT * FROM budgets WHERE id = :id")
    suspend fun getById(id: Long): BudgetEntity?

    @Query("SELECT * FROM budgets WHERE period = :period")
    suspend fun getByPeriod(period: String): List<BudgetEntity>

    @Query("""
        SELECT * FROM budgets 
        WHERE start_date <= :currentDate 
        AND (end_date IS NULL OR end_date >= :currentDate)
    """)
    suspend fun getActiveBudgets(currentDate: Long): List<BudgetEntity>

    @Query("""
        SELECT * FROM budgets 
        WHERE category_id = :categoryId 
        AND period = :period
        AND (end_date IS NULL OR end_date >= :currentDate)
    """)
    suspend fun getCurrentBudget(
        categoryId: Long,
        period: String,
        currentDate: Long
    ): BudgetEntity?

    @Query("""
        UPDATE budgets 
        SET spent = spent + :amount 
        WHERE category_id = :categoryId
    """)
    suspend fun updateSpent(categoryId: Long, amount: Double)

    @Query("""
        SELECT b.* FROM budgets b
        JOIN categories c ON b.category_id = c.id
        WHERE c.type = :type
    """)
    suspend fun getBudgetsByTransactionType(type: String): List<BudgetEntity>
}