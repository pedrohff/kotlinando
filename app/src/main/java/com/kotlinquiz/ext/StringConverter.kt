package com.kotlinquiz.ext

import android.arch.persistence.room.TypeConverter

/**
 * Created by pedro on 07/11/17.
 */
class StringConverter {
    @TypeConverter
    fun toString(list: List<String>) : String {
        var string = ""
        for (s in list) {
            string+=s+"|"
        }
        return string
    }

    @TypeConverter
    fun toList(string: String) : List<String> {
        return string.split('|')
    }
}