package br.edu.ifpb.jogodaforcafinal.jogo

import android.app.Instrumentation
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import br.edu.ifpb.jogodaforcafinal.R

class Resultado : AppCompatActivity() {
    private lateinit var resultado:      ImageView
    private lateinit var jogarNovamente: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado)

        this.resultado      = findViewById(R.id.imgResult)
        this.jogarNovamente = findViewById(R.id.btnJogarNovamente)

        if (intent.hasExtra("resultado")) {
            if (intent.getStringExtra("resultado") == "win") {
                this.resultado.setImageResource(
                    resources.getIdentifier("win", "drawable", packageName)
                )
            }
            else {
                this.resultado.setImageResource(
                    resources.getIdentifier("lose", "drawable", packageName)
                )
            }
        }

        this.jogarNovamente.setOnClickListener {
            val intent = Intent(this, JogoForca::class.java)

            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            }
        }
    }
}