package com.kotlinquiz.view

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kotlinquiz.R
import com.kotlinquiz.model.LogQuestao
import kotlinx.android.synthetic.main.activity_view_feedback.*

class ViewFeedback(var logQuestao: LogQuestao = LogQuestao()) : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_feedback)

        logQuestao = intent.getSerializableExtra("logQuestao") as LogQuestao
        atualizarTela()
        btTelaPrincipal.setOnClickListener { btTelaPrincipal() }
        btJogarNovamente.setOnClickListener { btNovaQuestao() }
    }

    fun atualizarTela() {
        if(logQuestao.pontuacao > 0) {
            tvTitulo.text = "Resposta\nCorreta"
            tvTitulo.setTextColor(Color.parseColor("#5fba7d"))
        } else {
            tvTitulo.text = "Resposta\nErrada"
            tvTitulo.setTextColor(Color.parseColor("#a06565"))
        }

        tvPontuacao.text = logQuestao.pontuacao.toString()
    }

    fun btNovaQuestao() {
        var intent : Intent = Intent(this,ViewQuestao::class.java)
        startActivity(intent)
        finish()
    }

    fun btTelaPrincipal() {
        var intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
