package com.eapvlab.movieapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.eapvlab.movieapp.data.model.MovieEntity

@Database(entities = [MovieEntity::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {

        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getDatabase(context: Context): AppDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "movie_table"
                ).build()
                INSTANCE = instance
                instance
            }
        }

        fun destroyInstance(){
            INSTANCE = null
        }
    }

}