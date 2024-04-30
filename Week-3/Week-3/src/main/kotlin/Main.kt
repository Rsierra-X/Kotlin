package org.example

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    var option: Int
    do {
        println("Menú:")
        println("1. Mostrar números pares en un rango")
        println("2. Verificar si una cadena es palíndromo")
        println("3. Conversor de temperaturas (Fahrenheit <-> Celsius)")
        println("4. Trabajar con la clase Nombre")
        println("5. Salir")
        print("Ingrese una opción (1-5): ")

        try {
            option = readLine()?.toInt() ?: 0
            when (option) {
                1 -> {
                    print("Ingrese el número inicial: ")
                    val start = readLine()?.toInt() ?: 0
                    print("Ingrese el número final: ")
                    val end = readLine()?.toInt() ?: 0
                    numerosPares(start, end)
                }
                2 -> {
                    print("Ingrese una palabra para verificar si es palíndromo: ")
                    val input = readLine() ?: ""
                    println("¿Es palíndromo? ${input.palindrome()}")
                }
                3 -> {
                    print("Ingrese un valor de temperatura: ")
                    val valor = readLine()?.toDouble() ?: 0.0
                    print("Ingrese 'C' para convertir a Celsius o 'F' para Fahrenheit: ")
                    val tipo = readLine() ?: ""
                    convertirTemperatura(valor, tipo)
                }
                4 -> {
                    trabajarConNombre()
                }
                5 -> {
                    println("¡Adiós!")
                }
                else -> println("Opción inválida. Inténtelo de nuevo.")
            }
        } catch (e: NumberFormatException) {
            println("Error: Ingrese un número válido.")
            option = 0
        }
    } while (option != 5)
}

fun numerosPares(start: Int, end: Int) {
    println("Números pares entre $start y $end:")
    for (i in start .. end) {
        if (i % 2 == 0) {
            print("$i ")
        }
    }
    println()
}

fun String.palindrome(): Boolean {
    val takeOutSpaces = this.replace("\\s+".toRegex(), "").toLowerCase()
    return takeOutSpaces == takeOutSpaces.reversed()
}

fun convertirTemperatura(valor: Double, tipo: String) {
    when (tipo.toUpperCase()) {
        "C" -> {
            val celsius = (valor - 32) * 5 / 9
            println("$valor Fahrenheit es $celsius Celsius")
        }
        "F" -> {
            val fahrenheit = valor * 9 / 5 + 32
            println("$valor Celsius es $fahrenheit Fahrenheit")
        }
        else -> println("Tipo de conversión no válido. Ingrese 'C' o 'F'.")
    }
}

fun trabajarConNombre() {
    val nombre = Nombre("Juan", "José", "Pérez", "López")
    println("Primer nombre: ${nombre.getPrimerNombre()}")
    println("Segundo nombre: ${nombre.getSegundoNombre()}")
    println("Primer apellido: ${nombre.getPrimerApellido()}")
    println("Segundo apellido: ${nombre.getSegundoApellido()}")
    println("Iniciales: ${nombre.getIniciales()}")
    println("Nombre completo: ${nombre.getNombreCompleto()}")

    val hijo = nombre.creaHijo("Pedro", "Pérez", "Gómez")
    println("Hijo creado: ${hijo.getNombreCompleto()}")
}

class Nombre(
    private val primerNombre: String,
    private val segundoNombre: String,
    private val primerApellido: String,
    private val segundoApellido: String
) {
    fun getPrimerNombre(): String = primerNombre
    fun getSegundoNombre(): String = segundoNombre
    fun getPrimerApellido(): String = primerApellido
    fun getSegundoApellido(): String = segundoApellido

    fun getIniciales(): String {
        return "${primerNombre.first()}.${segundoNombre.first()}.${primerApellido.first()}.${segundoApellido.first()}."
    }

    fun getNombreCompleto(): String {
        return "$primerNombre $segundoNombre $primerApellido $segundoApellido"
    }

    fun creaHijo(primerNombreHijo: String, segundoNombreHijo: String, segundoApellidoHijo: String): Nombre {
        return Nombre(primerNombreHijo, segundoNombreHijo, primerApellido, segundoApellidoHijo)
    }
}