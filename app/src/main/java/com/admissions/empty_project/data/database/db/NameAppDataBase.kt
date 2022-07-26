package com.admissions.empty_project.data.database.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.admissions.empty_project.data.database.dao.UserDao
import com.admissions.empty_project.data.database.entity.UserEntity


@Database(
    entities = [UserEntity::class],
    version = 1
)

abstract class NameAppDataBase : RoomDatabase() {
    abstract fun anyDao(): UserDao
    companion object{
        @Volatile
        private var INSTANCE: NameAppDataBase? = null

        fun getInstance(context: Context): NameAppDataBase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        NameAppDataBase::class.java,
                        "database-empty-app"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}