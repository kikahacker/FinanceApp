package com.example.financeapp.data.database.DAO

import androidx.room.*
import com.example.financeapp.data.database.entities.TransactionEntity

@Dao
interface TransactionDao {
    @Insert
    suspend fun insert(transaction: TransactionEntity): Long

    @Update
    suspend fun update(transaction: TransactionEntity)

    @Delete
    suspend fun delete(transaction: TransactionEntity)

    @Query("SELECT * FROM transactions")
    suspend fun getAll(): List<TransactionEntity>

    @Query("SELECT * FROM transactions WHERE id = :id")
    suspend fun getById(id: Long): TransactionEntity?

    @Query("SELECT * FROM transactions WHERE type = :type")
    suspend fun getByType(type: String): List<TransactionEntity>

    @Query("SELECT * FROM transactions WHERE category_id = :categoryId")
    suspend fun getByCategory(categoryId: Long): List<TransactionEntity>

    @Query("SELECT * FROM transactions WHERE date BETWEEN :start AND :end")
    suspend fun getByDateRange(start: Long, end: Long): List<TransactionEntity>

    @Query("SELECT SUM(amount) FROM transactions WHERE type = :type")
    suspend fun getTotalAmountByType(type: String): Double?

    @Query("""
        SELECT * FROM transactions 
        WHERE strftime('%Y-%m', date/1000, 'unixepoch') = :monthYear
        ORDER BY date DESC
    """)
    suspend fun getByMonth(monthYear: String): List<TransactionEntity>

    @Query("""
        SELECT category_id, SUM(amount) as total 
        FROM transactions 
        WHERE type = :type AND date BETWEEN :start AND :end
        GROUP BY category_id
    """)
    suspend fun getCategoryTotals(type: String, start: Long, end: Long): List<CategoryTotal>

    data class CategoryTotal(
        val category_id: Long,
        val total: Double
    )
}