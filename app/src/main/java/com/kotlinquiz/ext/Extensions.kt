package com.kotlinquiz.ext

import android.content.Context
import com.kotlinquiz.dao.LogQuestaoDatabase
import com.kotlinquiz.model.LogQuestao
import java.util.*

/**
 * Created by pedro on 07/11/17.
 */
fun <T:Comparable<T>>shuffle(items:MutableList<T>):List<T>{
    val rg = Random()
    for (i in 0 until (items.size - 1)) {
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

fun saveLog(context: Context,logQuestao: LogQuestao): Long {
    val db = LogQuestaoDatabase.getDB(context)
    return db.logQuestaoDao().save(logQuestao)
}

fun loadLogs(context: Context) : List<LogQuestao> {
    val db = LogQuestaoDatabase.getDB(context)
    return db.logQuestaoDao().getAll()
}

fun deleteAll(context: Context) {
    val db = LogQuestaoDatabase.getDB(context)
    db.logQuestaoDao().deleteAll()
}
