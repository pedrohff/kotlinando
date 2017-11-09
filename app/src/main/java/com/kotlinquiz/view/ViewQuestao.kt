package com.kotlinquiz.view

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.support.v7.app.AppCompatActivity
import android.view.View
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


class ViewQuestao (var segundos:Int = 60, var questao: Questao = Questao(), var countDown: CountDownTimer? = null) : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_questao)
        AutofitHelper.create(tvPergunta)

        contador()
        questaoAleatoria()

        resp1.setOnClickListener { validaQuestao(resp1.text.toString()) }
        resp2.setOnClickListener { validaQuestao(resp2.text.toString()) }
        resp3.setOnClickListener { validaQuestao(resp3.text.toString()) }
        resp4.setOnClickListener { validaQuestao(resp4.text.toString()) }



    }

    override fun onPostResume() {
        super.onPostResume()
        //adição do auto fit no texto de pergunta, próximo teste nos botões.
        //autofit text view tvPergunta
        //autofit text button
    }

    fun contador() {
        Thread(Runnable { runOnUiThread({
            countDown = object : CountDownTimer(60000,1000){
                override fun onTick(millisRestante: Long) {
                    segundos = (millisRestante/1000).toInt()
                    val porcentagem:Int = (segundos/0.6).toInt()
                    barra.progress = porcentagem
                    print("teste - " + millisRestante)
                    //ANIMAÇÃO DE PERIGO
                }

                override fun onFinish() {
//                    animarTempo(100)
                }
            }

            countDown?.start()
            })
        }).start()
    }

   /* @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private fun animarTempo(progress: Int) {
        if (barra.progress < 80){
            imagem_perigo.alpha = 0.3f;
            fadeAnimation(imagem_perigo, 3000)
        } else if (barra.progress < 70) {
            imagem_perigo.alpha = 0.5f;
            fadeAnimation(imagem_perigo, 2000)
        } else if (barra.progress < 50) {
            imagem_perigo.alpha = 0.7f;
            fadeAnimation(imagem_perigo, 1000)
        } else if (barra.progress < 50){
            imagem_perigo.alpha = 0.8f;
            fadeAnimation(imagem_perigo, 500)
        } else {
            imagem_perigo.alpha = 0.0f;
            imagem_perigo.animation?.cancel()
        }
    }*/

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
        resp1.text = questao.opcoes[0]
        resp2.text = questao.opcoes[1]
        resp3.text = questao.opcoes[2]
        resp4.text = questao.opcoes[3]
    }

    fun validaQuestao(resp:String) {
        countDown?.cancel()


        val log = LogQuestao(questaoId = questao.codigo, resposta = resp, tempoResposta = segundos)
        log.calculaPontuacao(this)

        val intent : Intent = Intent(this, ViewFeedback::class.java)
        intent.putExtra("logQuestao", log)
        saveLog(this,log)

        startActivity(intent)
        finish()
    }

    fun fadeAnimation(v: View, time: Long){
        val fadeOut = ObjectAnimator.ofFloat(v, "alpha", 1f, .3f)
        fadeOut.duration = time
        val fadeIn = ObjectAnimator.ofFloat(v, "alpha", .3f, 1f)
        fadeIn.duration = time

        val mAnimationSet = AnimatorSet()

        mAnimationSet.play(fadeIn).after(fadeOut)

        mAnimationSet.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                mAnimationSet.start()
            }
        })
        mAnimationSet.start()
    }
}
