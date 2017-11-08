package com.kotlinquiz.ext

import android.arch.persistence.room.Room
import android.content.Context
import android.os.AsyncTask
import com.kotlinquiz.dao.DB
import com.kotlinquiz.dao.LogQuestaoDao
import com.kotlinquiz.dao.LogQuestaoDatabase
import com.kotlinquiz.model.LogQuestao
import java.util.*

/**
 * Created by pedro on 07/11/17.
 */
fun <T:Comparable<T>>shuffle(items:MutableList<T>):List<T>{
    val rg : Random = Random()
    for (i in 0..items.size - 1) {
        val randomPosition = rg.nextInt(items.size)
        val tmp : T = items[i]
        items[i] = items[randomPosition]
        items[randomPosition] = tmp
    }
    return items
}

/* extension version */
fun <T> Iterable<T>.shuffle(): List<T> {
    val list = this.toMutableList().apply {  }
    Collections.shuffle(list)
    return list
}

fun saveLog(context: Context,logQuestao: LogQuestao) {
    Room.databaseBuilder(context, LogQuestao::class.java, LogQuestaoDatabase)

    object : AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg voids: Void): Void? {
            LogQuestaoDatabase.getInstance(context)?.save(logQuestao)
            return null
        }
    }.execute()
}

fun loadLogs(context: Context) : List<LogQuestao>? {
    var list : List<LogQuestao>? = null
    object : AsyncTask<Void, Void, List<LogQuestao>>() {
        override fun doInBackground(vararg voids: Void): List<LogQuestao>? {
            list = LogQuestaoDatabase.getInstance(context)?.getAll()
            return null
        }
    }.execute()

    return list
}