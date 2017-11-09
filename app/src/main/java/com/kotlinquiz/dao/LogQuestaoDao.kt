package com.kotlinquiz.dao
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import com.kotlinquiz.model.LogQuestao

@Dao interface LogQuestaoDao {

    @Query("select * from logQuestao")
    fun getAll() : List<LogQuestao>

    @Insert
    fun save(logs: LogQuestao) : Long

    @Update(onConflict = REPLACE)
    fun update(log: LogQuestao)

}