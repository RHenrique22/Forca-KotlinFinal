package br.edu.ifpb.jogodaforcafinal.jogo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import br.edu.ifpb.jogodaforcafinal.R
import br.edu.ifpb.jogodaforcafinal.fachada.Fachada

class JogoForca : AppCompatActivity() {
    private lateinit var control:    Fachada
    private lateinit var dica:       TextView
    private lateinit var palavra:    TextView
    private lateinit var status:     TextView
    private lateinit var input:      EditText
    private lateinit var button:     Button
    private lateinit var imgForca:   ImageView
    private lateinit var controlImg: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jogo_forca)

        this.control = Fachada()
        this.control.registroPadrao()
        this.control.iniciar()

        // SELECT ELEMENTO PELO ID
        this.dica         = findViewById(R.id.txtViewDica)
        this.palavra      = findViewById(R.id.txtViewPalavra)
        this.status       = findViewById(R.id.txtViewStatus)
        this.button       = findViewById(R.id.btnArriscar)
        this.input        = findViewById(R.id.editTxtInput)
        this.imgForca     = findViewById(R.id.imgViewForca)

        // MOSTRAR NA TELA O INIT DA FORCA
        this.dica.text    = this.control.dica()
        this.palavra.text = this.control.palavra()
        this.status.text  = this.control.status()
        this.controlImg   = "part${6 - this.control.penalidade()}"

        this.imgForca.setImageResource(
            resources.getIdentifier(controlImg, "drawable", packageName)
        )

        this.button.setOnClickListener {
            val letra      = this.input.text.toString()
            var exception  = ""
            exception     += this.control.jogar(letra)

            if (exception != "") {
                Toast.makeText(this, exception, Toast.LENGTH_SHORT).show()
            }

            this.controlImg = "part${6 - this.control.penalidade()}"

//                ATT ESTADO DA PALAVRA E ATT STATUS
            this.palavra.text = this.control.palavra()
            this.status.text  = this.control.status()

            this.imgForca.setImageResource(
                resources.getIdentifier(controlImg, "drawable", packageName)
            )

            if (this.control.terminou()) {

                this.button.isEnabled = false

                if (this.control.ganhou()) {
                    val intent = Intent(this, Resultado::class.java).apply {
                        putExtra("resultado", "win")
                    }

                    if (intent.resolveActivity(packageManager) != null) {
                        startActivity(intent)
                    }
                }
                else {
                    val intent = Intent(this, Resultado::class.java).apply {
                        putExtra("resultado", "lose")
                    }

                    if (intent.resolveActivity(packageManager) != null) {
                        startActivity(intent)
                    }
                }
            }
        }
    }
}