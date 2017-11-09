package com.kotlinquiz.ext

import android.content.Context
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

fun saveLog(context: Context,logQuestao: LogQuestao): Long {
    val db = LogQuestaoDatabase.getDB(context)
    var x: Long
    
    x = db.logQuestaoDao().save(logQuestao)
    return x
}

fun loadLogs(context: Context) : List<LogQuestao>? {
    val db = LogQuestaoDatabase.getDB(context)
    var list : List<LogQuestao>? = null
    list = db.logQuestaoDao().getAll()

    return list
}