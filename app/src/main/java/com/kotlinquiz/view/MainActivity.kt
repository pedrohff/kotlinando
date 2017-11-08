package com.kotlinquiz.view

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.support.annotation.UiThread
import android.support.v7.app.AppCompatActivity
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import com.kotlinquiz.R
import com.kotlinquiz.dao.LogQuestaoDatabase
import com.kotlinquiz.ext.loadLogs
import com.kotlinquiz.model.LogQuestao
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var media: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btJogar.setOnClickListener {
            pararMusica()
            val intent = Intent(this, ViewQuestao::class.java)
            startActivity(intent)
        }

        var pontuacao: Double?
        var listLog = loadLogs(this)
        pontuacao = listLog?.fold(0.0) { acc: Double, logQuestao: LogQuestao -> acc + logQuestao.pontuacao }

        if (pontuacao != null)
            tvPontuacao.text = pontuacao.toString()
        animarBotao();

    }


    override fun onStart() {
        super.onStart()
        iniciarMusica()
    }

    override fun onStop() {
        pararMusica()
        super.onStop()
    }

    override fun onResume() {
        super.onResume()
        var pontuacao: Double?
        val listLog = loadLogs(this)
        pontuacao = listLog?.fold(0.0) { acc: Double, logQuestao: LogQuestao -> acc + logQuestao.pontuacao }

        if (pontuacao != null)
            tvPontuacao.text = pontuacao.toString()
    }

    fun animarBotao() {
        val scaleAnimation = ScaleAnimation(0.7f, 1f, 0.7f, 1f, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f)
        scaleAnimation.repeatCount = Animation.INFINITE
        scaleAnimation.repeatMode = Animation.REVERSE
        scaleAnimation.duration = 2000
        btJogar.startAnimation(scaleAnimation)
    }

    fun iniciarMusica() {
        if (media == null)
            media = MediaPlayer.create(this, R.raw.main_music)
        Thread(Runnable {
            if (!media?.isPlaying!!) {
                media?.isLooping = true
                media?.start();
            }
        }).start()
    }

    fun pararMusica() {
        if (media != null) {
            if (media?.isPlaying!!) {
                media?.stop();
                media?.release()
                media = null
                // media?.release()
                //media = null;
            }
        }
    }
}

