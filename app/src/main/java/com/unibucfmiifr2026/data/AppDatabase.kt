package com.unibucfmiifr2026.data

import android.location.Address
import androidx.room.Database
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.unibucfmiifr2026.ApplicationController
import com.unibucfmiifr2026.data.dao.AddressDAO
import com.unibucfmiifr2026.data.dao.UserDAO
import com.unibucfmiifr2026.data.entities.AddressEntity
import com.unibucfmiifr2026.data.entities.UserEntity

@Database(entities = [UserEntity::class, AddressEntity::class], version = 5)
abstract class AppDatabase: RoomDatabase() {
    companion object {
        @Volatile
        private var instance: AppDatabase? = null
        private val migration_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE users RENAME COLUMN firstName TO first_name")
                db.execSQL("ALTER TABLE users RENAME COLUMN lastName TO last_name")
            }
        }

        private val migration_2_3 = object : Migration(2, 3) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE users ADD COLUMN email TEXT NOT NULL DEFAULT 'default_email'")
            }
        }

        fun getDatabase() : AppDatabase {
            return instance ?: synchronized(this) {
                val newInstance = databaseBuilder(
                    ApplicationController.instance,
                    AppDatabase::class.java,
                    "app_database"
                )
//                    .addMigrations(migration_1_2, migration_2_3)
                    .fallbackToDestructiveMigration(false)
                    .build()
                instance = newInstance
                newInstance
            }
        }
    }


    abstract fun userDAO(): UserDAO
    abstract fun addressDAO(): AddressDAO
}