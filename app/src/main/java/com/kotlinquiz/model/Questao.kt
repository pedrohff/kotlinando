package com.kotlinquiz.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by pedro on 06/11/17.
 */
@Entity(tableName = "questao")
data class Questao constructor(
        @PrimaryKey(autoGenerate = true)
        var codigo: Int? = null,
        var pergunta: String,
        var opcoes: List<String>,
        var respostaCorreta: String) {
    constructor() : this(0,"", emptyList(), "")
}