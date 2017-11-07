package com.kotlinquiz.view

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kotlinquiz.R
import com.kotlinquiz.ext.loadLogs
import com.kotlinquiz.model.LogQuestao
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btJogar.setOnClickListener {
            pararMusica()
            val intent = Intent(this, ViewQuestao::class.java)
            startActivity(intent)
        }

        var pontuacao:Double?
        val listLog = loadLogs()
        pontuacao = listLog?.fold(0.0){acc: Double, logQuestao: LogQuestao ->  acc+logQuestao.pontuacao}

        if(pontuacao != null)
            tvPontuacao.text = pontuacao.toString()
        animarBotao();
        iniciarMusica();
    }

    fun animarBotao(){

    }

    fun iniciarMusica(){

    }

    fun pararMusica(){

    }


}
