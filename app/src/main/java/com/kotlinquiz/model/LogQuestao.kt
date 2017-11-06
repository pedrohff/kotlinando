import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "logQuestao")
class LogQuestao(@PrimaryKey(autoGenerate = true) var codigo:Int, var questao: Questao, var resposta:String, var tempoResposta:Int, var pontuacao:Double) {


    private fun validaResposta():Boolean = (this.questao.respostaCorreta == this.resposta)

    fun calculaPontuacao() {
        if(validaResposta()){
            this.pontuacao = (tempoResposta+45.0) %100
        }else{
            this.pontuacao = 0.0
        }
    }
}