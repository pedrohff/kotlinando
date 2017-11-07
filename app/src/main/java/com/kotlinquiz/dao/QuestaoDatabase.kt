package com.kotlinquiz.dao

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.kotlinquiz.ext.StringConverter
import com.kotlinquiz.model.Questao

/**
 * Created by pedro on 07/11/17.
 */

@Database(entities = arrayOf(Questao::class), version = 1, exportSchema = false)
@TypeConverters(StringConverter::class)
abstract class QuestaoDatabase : RoomDatabase() {
    abstract fun questaoDao() : QuestaoDao
}