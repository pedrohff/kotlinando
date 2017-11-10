package com.kotlinquiz.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.content.Context
import com.google.gson.Gson
import com.kotlinquiz.R
import java.io.InputStream
import java.io.Serializable

@Entity(tableName = "logQuestao")
data class LogQuestao(var questaoId: Int? = 0, var resposta:String?, var tempoResposta:Int = 0, var pontuacao:Double = 0.0) : Serializable {

    @PrimaryKey
    var codigo:Int? = null

    constructor() : this(0,null,0,0.0)

    private fun validaResposta(context: Context):Boolean{
        val questao : Questao = buscarNaLista(questaoId,context)
        return questao.respostaCorreta.toLowerCase() == this.resposta?.toLowerCase()
    }

    private fun buscarNaLista(int: Int?,context: Context) : Questao{
        val inputStream : InputStream = context.resources.openRawResource(R.raw.questoes)
        val arquivo = inputStream.bufferedReader().use { it.readText() }

        val gson = Gson()
        val lista : List<Questao> = gson.fromJson(arquivo, Array<Questao>::class.java).toList()
        return lista.filter{ it.codigo == int }.get(0)
    }

    fun calculaPontuacao(context: Context) {
        if(validaResposta(context) && resposta!=null){
            this.pontuacao = tempoResposta + 40.0
        }else{
            this.pontuacao = 0.0
        }
    }
}