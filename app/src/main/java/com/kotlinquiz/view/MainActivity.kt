package com.kotlinquiz.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.kotlinquiz.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btJogar.setOnClickListener {
            val intent = Intent(this, ViewQuestao::class.java)
            startActivity(intent)
        }
    }

}
