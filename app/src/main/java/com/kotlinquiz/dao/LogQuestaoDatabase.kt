package com.kotlinquiz.dao

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.kotlinquiz.ext.StringConverter
import com.kotlinquiz.model.LogQuestao

/**
 * Created by pedro on 06/11/17.
 */

@Database(entities = arrayOf(LogQuestao::class), version = 1, exportSchema = false)
@TypeConverters(StringConverter::class)
abstract class LogQuestaoDatabase : RoomDatabase() {
    abstract fun logQuestaoDao() : LogQuestaoDao

}