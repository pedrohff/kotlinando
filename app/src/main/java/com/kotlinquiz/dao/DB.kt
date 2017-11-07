package com.kotlinquiz.dao

import android.app.Application
import android.arch.persistence.room.Room

/**
 * Created by pedro on 07/11/17.
 */
class DB: Application() {

    companion object {
        var database:LogQuestaoDatabase? = null
    }

    override fun onCreate() {
        super.onCreate()
        DB.database = Room.databaseBuilder(this, LogQuestaoDatabase::class.java, "abc").build()
    }
}