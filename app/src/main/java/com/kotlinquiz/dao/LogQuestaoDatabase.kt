package com.kotlinquiz.dao

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.kotlinquiz.model.LogQuestao

/**
 * Created by pedro on 06/11/17.
 */

@Database(entities = arrayOf(LogQuestao::class), version = 2, exportSchema = false)
//@TypeConverters(StringConverter::class)
abstract class LogQuestaoDatabase : RoomDatabase() {



    abstract fun logQuestaoDao() : LogQuestaoDao

    companion object {
        var INSTANCE : LogQuestaoDatabase?= null
        val databaseName = "DB"

        fun getDB(context: Context) : LogQuestaoDatabase {
            if(INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.applicationContext, LogQuestaoDatabase::class.java, databaseName).fallbackToDestructiveMigration().allowMainThreadQueries().build()
            }
            return INSTANCE as LogQuestaoDatabase
        }

        fun destroyDB() {
            INSTANCE = null
        }
    }

    /*companion object{


        var dbInstance:LogQuestaoDao? = null
        fun getInstance(context: Context):LogQuestaoDao?{
            if(dbInstance == null)
                dbInstance = Room.inMemoryDatabaseBuilder(context, LogQuestaoDatabase::class.java).build().logQuestaoDao()
            return dbInstance;
        }
    }*/
}