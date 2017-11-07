package com.kotlinquiz.dao

import android.arch.persistence.room.*
import com.kotlinquiz.model.Questao

/**
 * Created by pedro on 07/11/17.
 */
@Dao interface QuestaoDao {

    @Query("select * from questao")
    fun getAll() : List<Questao>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(q: Questao)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(q: Questao)
}