package com.kotlinquiz.view

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.gson.Gson
import com.kotlinquiz.R
import com.kotlinquiz.ext.saveLog
import com.kotlinquiz.ext.shuffle
import com.kotlinquiz.model.LogQuestao
import com.kotlinquiz.model.Questao
import kotlinx.android.synthetic.main.activity_view_questao.*
import me.grantland.widget.AutofitHelper
import java.io.InputStream
import java.util.*



class ViewQuestao(var questao: Questao = Questao()) : AppCompatActivity(), MediaPlayer.OnPreparedListener {


    var tik_tak_music: MediaPlayer? = null
    var duration = 0


    //MediaPlayer Progress Handler
    private var onEverySecond: Runnable = object : Runnable {
        override fun run() {
            if (barra != null) {
                barra.progress = barra.max - (tik_tak_music?.currentPosition ?: 0)
                updateAction()
            }

            if (tik_tak_music?.isPlaying == true) {
                barra.postDelayed(this, 200)
            }
        }
    }

    private fun updateAction() {
        if (tik_tak_music?.currentPosition!! >= tik_tak_music?.duration!!) {
            pararMusica()
            validaQuestao(null)
        }
    }


    fun getVolume(): Float {
        val currVolume = 5.0f
        val maxVolume = 15.0f
        return currVolume / maxVolume
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_questao)
        AutofitHelper.create(tvPergunta)

        tik_tak_music = MediaPlayer.create(this, R.raw.questoes_music)
        tik_tak_music?.setVolume(getVolume(), getVolume())
        tik_tak_music?.setOnPreparedListener(this)

        questaoAleatoria()

        resp1.setOnClickListener { validaQuestao(resp1.text.toString()) }
        resp2.setOnClickListener { validaQuestao(resp2.text.toString()) }
        resp3.setOnClickListener { validaQuestao(resp3.text.toString()) }
        resp4.setOnClickListener { validaQuestao(resp4.text.toString()) }
    }

    override fun onStart() {
        super.onStart()
        reproduzirMusica()
    }

    private fun reproduzirMusica() {
        Thread(Runnable {
            tik_tak_music?.start()
        }).start()
    }

    override fun onPause() {
        super.onPause()
        pararMusica()
    }

    private fun pararMusica() {
        Thread(Runnable {
            tik_tak_music?.pause()
        }).start()
    }

    fun lerQuestoes() : List<Questao> {
        val inputStream : InputStream = resources.openRawResource(R.raw.questoes)
        val arquivo = inputStream.bufferedReader().use { it.readText() }

        val gson = Gson()
        return gson.fromJson(arquivo, Array<Questao>::class.java).toList()
    }

    fun questaoAleatoria() {
        val listaQuestoes:List<Questao> = lerQuestoes()
        val random = Math.abs(Random().nextInt() % listaQuestoes.size)
        this.questao = listaQuestoes.get(random)

        //questao = Questao(codigo = 0, pergunta = "a", respostaCorreta = "q", opcoes = listOf("q", "w", "e", "r"))
        this.questao.opcoes.shuffle()
        setQuestao()
    }

    fun setQuestao() {
        tvPergunta.text = questao.pergunta
//        AutofitHelper.create(tvPergunta)
        resp1.text = questao.opcoes[0]
        resp2.text = questao.opcoes[1]
        resp3.text = questao.opcoes[2]
        resp4.text = questao.opcoes[3]
    }

    fun validaQuestao(resp:String?) {

        val segundos = ((tik_tak_music?.duration!! - tik_tak_music?.currentPosition!!) / 1000) % 60
        val log = LogQuestao(questaoId = questao.codigo, resposta = resp, tempoResposta = segundos)
        if(resp==null)
            log.tempoResposta = 0
        log.calculaPontuacao(this)

        val intent = Intent(this, ViewFeedback::class.java)
        intent.putExtra("logQuestao", log)
        saveLog(this,log)

        startActivity(intent)
        finish()
    }

    //MediaPlayer
    override fun onPrepared(p0: MediaPlayer?) {
        duration = tik_tak_music?.duration ?: 0
        barra.max = duration
        barra.postDelayed(onEverySecond, 200)
    }
}
