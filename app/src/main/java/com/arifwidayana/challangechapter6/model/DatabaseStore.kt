package com.arifwidayana.challangechapter6.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.arifwidayana.challangechapter6.model.dao.UserDao
import com.arifwidayana.challangechapter6.model.data.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class DatabaseStore : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        private var userDatabase: DatabaseStore? = null

        fun getData(context: Context): DatabaseStore {
            return userDatabase?: synchronized(this){
                val data = Room.databaseBuilder(
                    context.applicationContext,
                    DatabaseStore::class.java,
                    "Data.db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                userDatabase = data
                data
            }
        }
    }
}