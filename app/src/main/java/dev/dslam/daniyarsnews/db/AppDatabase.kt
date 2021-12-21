package dev.dslam.daniyarsnews.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.dslam.daniyarsnews.models.Article

@Database(entities = [Article::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getLocalNewsDao(): LocalNewsDao

    companion object {
        private var dbInstance: AppDatabase? = null

        fun getAppDB(context: Context): AppDatabase {
            if (dbInstance == null) {
                dbInstance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "daniyarsnews.db"
                )
                    .allowMainThreadQueries()
                    .build()
            }

            return dbInstance!!
        }
    }
}