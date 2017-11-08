package com.kotlinquiz.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "logQuestao")
data class LogQuestao(var questaoId: Int? = 0, var resposta:String = "", var tempoResposta:Int = 0, var pontuacao:Double = 0.0) : Serializable {

    @PrimaryKey(autoGenerate = true)
    var codigo:Int? = 0

    constructor() : this(0,"",0,0.0)

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