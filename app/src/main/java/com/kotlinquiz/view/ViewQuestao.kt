package com.kotlinquiz.view

import android.os.Bundle
import android.os.CountDownTimer
import android.support.v7.app.AppCompatActivity
import com.kotlinquiz.R
import com.kotlinquiz.ext.saveLog
import com.kotlinquiz.ext.shuffle
import com.kotlinquiz.model.LogQuestao
import com.kotlinquiz.model.Questao
import kotlinx.android.synthetic.main.activity_view_questao.*

class ViewQuestao (var segundos:Int = 60, var questao: Questao = Questao(), var countDown: CountDownTimer? = null) : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_questao)
        contador()
        questaoAleatoria()

        resp1.setOnClickListener { validaQuestao(resp1.text.toString()) }
        resp2.setOnClickListener { validaQuestao(resp2.text.toString()) }
        resp3.setOnClickListener { validaQuestao(resp3.text.toString()) }
        resp4.setOnClickListener { validaQuestao(resp4.text.toString()) }

    }

    fun contador() {
        countDown = object : CountDownTimer(60000,1000){
            override fun onTick(millisRestante: Long) {
                segundos = (millisRestante/1000).toInt()
                val porcentagem:Int = (segundos/0.6).toInt()
                barra.progress = porcentagem
                print("teste - " + millisRestante)
            }

            override fun onFinish() {
            }
        }

        countDown?.start()
    }

    fun questaoAleatoria() {
        //var listaQuestoes:List<Questao> = mutableListOf<Questao>()
        //Falta ler do arquivo json de questoes
        //var random = Random().nextInt() % listaQuestoes.size
        //this.questao = listaQuestoes.get(random)

        questao = Questao(codigo = 0, pergunta = "a", respostaCorreta = "q", opcoes = listOf("q", "w", "e", "r"))
        this.questao.opcoes.shuffle()
        setQuestao()
    }

    fun setQuestao() {
        tvPergunta.text = questao.pergunta
        resp1.text = questao.opcoes[0]
        resp2.text = questao.opcoes[1]
        resp3.text = questao.opcoes[2]
        resp4.text = questao.opcoes[3]
    }

    fun validaQuestao(resp:String) {
        countDown?.cancel()


        val log = LogQuestao(questaoId = questao.codigo, resposta = resp, tempoResposta = segundos)
        log.calculaPontuacao()

        saveLog(log)
    }
}
