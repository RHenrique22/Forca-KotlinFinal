package br.edu.ifpb.jogodaforcafinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import br.edu.ifpb.jogodaforcafinal.jogo.JogoForca

class MainActivity : AppCompatActivity() {
    lateinit var image:  ImageView
    lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.image  = findViewById(R.id.imgViewBackInit)
        this.button = findViewById(R.id.btnInit)

        this.image.setImageResource(
            resources.getIdentifier("testeforca", "drawable", packageName)
        )

        this.button.setOnClickListener {
            val intent = Intent(this, JogoForca::class.java)

            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            }
        }
    }
}