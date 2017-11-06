package com.kotlinquiz.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.kotlinquiz.R
import kotlinx.android.synthetic.main.activity_view_questao.*

class ViewQuestao(var segundos:Int) : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_questao)
        contador()
    }

    fun contador() {
        var countDown = object : CountDownTimer(60000,1000){
            override fun onTick(millisRestante: Long) {
                segundos = (millisRestante/1000).toInt()
                val porcentagem:Int = (segundos/0.6).toInt()
                barra.progress = porcentagem
                print("teste - " + millisRestante)
            }

            override fun onFinish() {
            }
        }

        countDown.start()
    }
}
