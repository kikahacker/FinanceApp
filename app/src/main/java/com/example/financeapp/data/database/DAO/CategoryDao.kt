package com.example.financeapp.data.database.DAO

import androidx.room.*
import com.example.financeapp.data.database.entities.CategoryEntity


@Dao
interface CategoryDao {
    @Insert
    suspend fun insert(category: CategoryEntity): Long

    @Update
    suspend fun update(category: CategoryEntity)

    @Delete
    suspend fun delete(category: CategoryEntity)

    @Query("SELECT * FROM categories")
    suspend fun getAll(): List<CategoryEntity>

    @Query("SELECT * FROM categories WHERE id = :id")
    suspend fun getById(id: Long): CategoryEntity?

    @Query("SELECT * FROM categories WHERE type = :type")
    suspend fun getByType(type: String): List<CategoryEntity>

    @Query("SELECT COUNT(*) FROM categories")
    suspend fun getCount(): Int

    @Query("""
        SELECT c.* FROM categories c
        LEFT JOIN transactions t ON c.id = t.category_id
        WHERE c.type = :type
        GROUP BY c.id
        ORDER BY COUNT(t.id) DESC
    """)
    suspend fun getMostUsedCategories(type: String): List<CategoryEntity>

    @Query("""
        SELECT c.* FROM categories c
        WHERE c.id NOT IN (
            SELECT DISTINCT category_id FROM transactions 
            WHERE date BETWEEN :startDate AND :endDate
        )
        AND c.type = :type
    """)
    suspend fun getUnusedCategories(
        type: String,
        startDate: Long,
        endDate: Long
    ): List<CategoryEntity>
}