package br.edu.ifpb.jogodaforcafinal.model

class Forca(private var palavra: String, private var dica: String) {
    private var letrasUsadas  = ""
    private var tamPalavra    = this.palavra.length
    private var palavraOculta = mutableListOf<Char>()
    private var tentativa     = 6
    private var acertos       = 0

    fun getPalavra(): String {
        return this.palavra
    }

    fun getDica(): String {
        return this.dica
    }

    fun getLetrasUsadas(): String {
        return this.letrasUsadas
    }

    fun getPalavraOculta(): String {
        return this.palavraOculta.joinToString(" ")
    }

    fun getTentativa(): Int {
        return this.tentativa
    }

    fun getAcertos(): Int {
        return this.acertos
    }

    fun getTamPalavra(): Int {
        return this.tamPalavra
    }

    fun ganhou() {
        this.acertos = this.palavra.length
    }

    fun perdeu() {
        this.tentativa = 0
    }

    fun iniciarJogo() {
        this.esconderPalavra()
    }

    fun esconderPalavra() {
        for (i in 0 until this.palavra.length) {
            this.palavraOculta.add('_')
        }
    }

    fun letraDistinta(): Int {
        var letrasDistinct = this.palavra.uppercase().toList()
        return letrasDistinct.distinct().size
    }

    fun arriscarLetra(letra: String): String? {
        if (letra.isNotEmpty()) {
            try {
                var existe = false

                this.testarLetra(letra)

                for (i in 0 until this.palavra.length) {
                    if (letra[0].uppercaseChar() == this.palavra[i].uppercaseChar()) {
                        this.acertos++
                        this.palavraOculta[i] = letra[0]
                        existe = true
                    }

                }

                if (!existe) {
                    this.tentativa--
                }
            } catch (e: Throwable) {
                return e.message
            }
        }

        return ""
    }

    fun testarLetra(letra: String) {
        if (this.letrasUsadas.contains(letra[0].uppercaseChar())) {
            throw Throwable("\nLetra ${letra[0].uppercaseChar()} já digitada, tente outra letra")
        } else {
            this.letrasUsadas += "${letra[0].uppercaseChar()} "
        }
    }

    fun terminou(): Boolean {
        return this.acertos == this.palavra.length || this.tentativa == 0
    }
}