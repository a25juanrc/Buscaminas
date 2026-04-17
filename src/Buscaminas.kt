class Buscaminas(filas: Int, columnas:Int,val minas: Int){
    private val buscaminas: MutableList<MutableList<String>>
    private val buscaminasjugador: MutableList<MutableList<String>>
    init {
        if (filas<1) {
            throw Exception("Deben ser dos o mas filas")
        }
        if (columnas<1) {
            throw Exception("Deben ser dos o mas columnas")
        }
        if (minas>=filas*columnas) {
            throw Exception("El numero de minas es mayor o igual al tamaño del tablero")
        }
        buscaminas= MutableList (filas) {MutableList (columnas) { "B" } }
        buscaminasjugador= MutableList (filas) {MutableList (columnas) { "?" } }
        for (i in 1..minas) {
            do {
                var x=true
                var minafila = (0..filas - 1).random()
                var minacolumna = (0..columnas - 1).random()
                if (buscaminas[minafila][minacolumna]=="B") {
                    buscaminas[minafila][minacolumna]="M"
                    x=false
                }
            } while (x)
        }
        for (i in buscaminas.indices) {
            for (j in buscaminas[i].indices) {
                if (buscaminas[i][j]!="M") {
                    var x=0
                    for (a in i-1..i+1) {
                        for (b in j-1..j+1) {
                            if (a in buscaminas.indices && b in buscaminas[a].indices && buscaminas[a][b]=="M") {
                                x=x+1
                            }
                        }
                    }
                    buscaminas[i][j]=x.toString()
                }
            }
        }
    }
    fun jugadamostrar(x:Int, y:Int, tipo: String):Boolean {
        if (tipo=="C") {
            if (buscaminas[x][y] == "M") {
                for (i in buscaminas.indices) {
                    for (j in buscaminas[i].indices) {
                        if (buscaminas[i][j] == "M") {
                            buscaminasjugador[i][j] = "M"
                        }
                    }
                }
                return false
            }
            buscaminasjugador[x][y] = buscaminas[x][y]
            if (buscaminasjugador[x][y] == "0") {
                var z = true
                while (z) {
                    z = false
                    for (i in buscaminasjugador.indices) {
                        for (j in buscaminasjugador[i].indices) {
                            if (buscaminasjugador[i][j] == "0") {
                                for (a in i - 1..i + 1) {
                                    for (b in j - 1..j + 1) {
                                        if (a in buscaminasjugador.indices && b in buscaminasjugador[a].indices && buscaminasjugador[a][b] == "?") {
                                            buscaminasjugador[a][b] = buscaminas[a][b]
                                            z = true
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                for (i in buscaminasjugador.indices) {
                    for (j in buscaminasjugador[i].indices) {
                        if (buscaminasjugador[i][j] == "0") {
                            buscaminasjugador[i][j] = " "
                        }
                    }
                }
            }
        } else {
            buscaminasjugador[x][y]="B"
        }

        return true
    }
    fun mostrarmapa(){
        for (i in buscaminasjugador.indices) {
            println(buscaminasjugador[i].joinToString(" "))
        }
    }
    fun mirarvictoria(): Boolean{
        var casillasocultas=0
        for (i in buscaminasjugador.indices) {
            for (j in buscaminasjugador[i].indices) {
                if (buscaminasjugador[i][j]=="?" || buscaminasjugador[i][j]=="B")
                    casillasocultas=casillasocultas+1
            }
        }
        return minas==casillasocultas
    }

}