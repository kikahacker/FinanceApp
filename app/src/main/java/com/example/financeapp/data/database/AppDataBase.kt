package com.example.financeapp.data.database


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.financeapp.data.database.DAO.BudgetDao
import com.example.financeapp.data.database.DAO.CategoryDao
import com.example.financeapp.data.database.DAO.TransactionDao
import com.example.financeapp.data.database.entities.BudgetEntity
import com.example.financeapp.data.database.entities.CategoryEntity
import com.example.financeapp.data.database.entities.TransactionEntity

@Database(
    entities = [
        TransactionEntity::class,
        BudgetEntity::class,
        CategoryEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun transactionDao(): TransactionDao
    abstract fun budgetDao(): BudgetDao
    abstract fun categoryDao(): CategoryDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "finance_database"
                )
                    .addCallback(object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            // Здесь можно добавить первоначальное заполнение БД
                        }
                    })
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}