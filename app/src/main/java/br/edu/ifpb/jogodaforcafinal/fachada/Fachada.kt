package br.edu.ifpb.jogodaforcafinal.fachada

import br.edu.ifpb.jogodaforcafinal.model.Forca
import br.edu.ifpb.jogodaforcafinal.repositorio.BancoPalavras


class Fachada {

    private var bancoForca = BancoPalavras()
    private lateinit var jogoDaForca: Forca

    fun registroPadrao() {
        bancoForca.add("Bola", "É redonda")
        bancoForca.add("Amarelo", "Cor da alegria")
        bancoForca.add("Chuva", "Cai em pé e corre deitado")
        bancoForca.add("Alho", "Tem cabeça e tem dente, não é bicho e nem é gente")
    }


/*
    Possível implementação para quando for incluído os inputs do cliente

    fun registrar(resposta: String): Boolean {
        if (resposta.uppercase().contains("S")) {
            print("\nDigite uma palavra: ")
            val palavra = input.nextLine()
            print("Digite uma Dica: ")
            val dica = input.nextLine()

            if (this.validar(palavra, dica)) {
                this.cadastro(palavra, dica)
            }
            else {
                println("É necessário uma palavra e uma dica")
            }

            return true
        }

        return false
    }

    fun cadastro(palavra: String, dica: String) {
        bancoForca.add(palavra, dica)
    }
*/

    fun sortear() {
        this.bancoForca.sortearPalavra()
        jogoDaForca = Forca(bancoForca.getPalavra(), bancoForca.getDica())
    }

    fun validar(palavra: String, dica: String): Boolean {
        return palavra.isNotEmpty() && dica.isNotEmpty()
    }

    fun iniciar() {
        this.sortear()
        jogoDaForca.iniciarJogo()
    }

    fun jogar(palavra: String): String? {
        return jogoDaForca.arriscarLetra(palavra.trim())
    }

    fun dica(): String {
        var output  = ""
        output     += "Dica:   ${jogoDaForca.getDica().uppercase()}"

        return output
    }

    fun palavra(): String {
        var output  = ""
        output     += "Palavra:  ${jogoDaForca.getPalavraOculta().uppercase()}"

        return output
    }

    fun status(): String {
        var output  = ""
        output     += "Quantidade de letras:   ${jogoDaForca.getTamPalavra()}\n"
        output     += "Letras já utilizadas:   [${jogoDaForca.getLetrasUsadas()}]\n"
        output     += "Quantidade de letras distintas:   ${jogoDaForca.letraDistinta()}\n"
        output     += "Quantidade de acertos:   ${jogoDaForca.getAcertos()}\n"
        output     += "Quantidade de tentativas:   ${jogoDaForca.getTentativa()}\n"

        /*
        if (this.perdeu()) {
            output += "\nA palavra era ${jogoDaForca.getPalavra()}\n"
            output += "\nPERDEU :-("
        }
        else if (this.ganhou()) {
            output += "\nA palavra era ${jogoDaForca.getPalavra()}\n"
            output += "\nGANHOU :-)"
        }
        else {
            output += ""
        }
        */

        return output
    }

    fun terminou(): Boolean {
        return jogoDaForca.terminou()
    }

    fun perdeu(): Boolean {
        return jogoDaForca.getTentativa() == 0
    }

    fun ganhou(): Boolean {
        return jogoDaForca.getAcertos() == jogoDaForca.getTamPalavra()
    }

    fun penalidade(): Int {
        return jogoDaForca.getTentativa()
    }
}