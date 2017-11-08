package com.kotlinquiz.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "logQuestao")
data class LogQuestao(@PrimaryKey(autoGenerate = true) var codigo:Int? = null, var questaoId: Int?, var resposta:String, var tempoResposta:Int, var pontuacao:Double = 0.0) : Serializable {

    constructor() : this(0,0,"",0,0.0)

    private fun validaResposta():Boolean{
        /*var questao : Questao
        questao == buscarNaLista(questaoId)
        return questao.respostaCorreta == this.resposta*/
        return true
    }

    fun calculaPontuacao() {
        if(validaResposta()){
            this.pontuacao = (tempoResposta+45.0) %100
        }else{
            this.pontuacao = 0.0
        }
    }
}