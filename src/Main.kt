fun main() {
    println("Dime el numero de filas")
    val filas=readln()
    println("Dime el numero de columnas")
    val columnas=readln()
    println("Dime el numero de minas")
    val minas=readln()
    var buscaminas = Buscaminas(filas.toInt(), columnas.toInt(), minas.toInt())
    buscaminas.mostrarmapa()
    do {
        println("Dime la fila de tu jugada")
        var filaj=readln()
        println("Dime la columna de tu jugada")
        var columnaj=readln()
        println("Quieres revelar [c]asilla o quieres poner [b]andera")
        var tipo=readln().uppercase()
        var a=buscaminas.jugadamostrar(filaj.toInt(), columnaj.toInt(),tipo)
        buscaminas.mostrarmapa()
        if (a) {
            var b = buscaminas.mirarvictoria()
            if (b) {
                println("Has ganado, enhorabuena")
                break
            }
        } else {
            println("Has perdido")
        }
    } while (a)
}b