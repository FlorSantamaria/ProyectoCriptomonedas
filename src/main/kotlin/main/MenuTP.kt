package main
import entidades.*
import excepciones.DineroInsuficienteException
import excepciones.LogueoIncorrectoException
import excepciones.OpcionIncorrectaException
import repositorios.CuentaRepositorio
import repositorios.UsuarioRepositorio
import java.lang.RuntimeException
import java.time.LocalDate
import java.time.LocalTime


fun main() {

    val usuarioRepo = UsuarioRepositorio
    val cuentas = CuentaRepositorio

    var nickname: String
    var password: String
    var validacionAny= false

    do {

        println("Ingrese nickname")
        nickname = readLine().toString()
        println("Ingrese password")
        password = readLine().toString()

try {
    validacionAny = usuarioRepo.usuarios.any { usuario -> usuario.nickname == nickname && usuario.password == password }
    if (validacionAny) {
        val usuario: Usuario? = usuarioRepo.iniciar(nickname, password)
        if (usuario != null) {
            println("-Iniciaste sesion")
        }
    } else {
        throw LogueoIncorrectoException()
    }
} catch (e: LogueoIncorrectoException){
    println("Usuario y/o contraseña inválidos")
}

    } while (!validacionAny)

        val cuenta = cuentas.buscarCuentaPertenecienteAlUsuario(nickname)
        if (cuenta != null) {
            println("Bienvenido/a a Orange ${cuenta.nombre}")
        }

        var opcion = 0
        var montoInicial= 0.0
        var exchange2: Exchange = Exchange.CRIPTOMAS
        var comision= 0.0
        var cashback= 0.0



        do {
            println("""
          Elige una opción 
          1- Para ver los datos de su cuenta
          2- Para comprar Bitcoins
          3 Para ver el historial de compras 
          4 Para salir"""
            )
            try {
                try {
                    opcion = readLine()?.toInt()!!
                    if (opcion<=0 || opcion>4){
                        throw OpcionIncorrectaException()
                    }
                }
                catch(e:OpcionIncorrectaException){
                    println("Ingrese un número del 1 al 4")
                }
            }catch(e:RuntimeException){
                println("Formato invalido. Ingrese una opción válida")
            }

            when (opcion) {

                1 -> println(cuenta.toString())

                2 -> {

                    val cripto = Bitcoin()
                    println("El precio actual de las Bitcoins es de $ ${cripto.precio} ")
                    println("Elija cantidad de Bitcoins a comprar")
                    val cantidad = readLine()
                    if (cantidad != null) {
                        montoInicial = cripto.precio.times(cantidad.toInt())
                    }


                    println("Elige el exchange \n 1- para CRIPTOMAS \n 2- para CRIPTODIA \n 3-para CARRECRIPTO")
                    val exchange = readLine()?.toInt()


                    val hora2 = LocalTime.of(20, 0)
                    val hora3 = LocalTime.of(23, 59)
                    when (exchange) {
                        1 -> {
                            comision = montoInicial.times(0.02)
                            println("Escogiste criptomas, se te aplicará una comisión de $comision")
                            exchange2 = Exchange.CRIPTOMAS
                        }

                        2 -> {
                            exchange2 = Exchange.CRIPTODIA
                            if (LocalTime.now() in hora2..hora3) {
                                comision = montoInicial.times(0.01)

                            } else {
                                comision = montoInicial.times(0.03)
                            }
                            println("Escogiste CRIPTODIA, se te aplicará una comisión de $comision")
                        }

                        3 -> {
                            exchange2 = Exchange.CARRECRIPTO
                            val dia = LocalDate.now().dayOfWeek.name
                            if (dia == "SATURDAY" || dia == "SUNDAY") {
                                comision = montoInicial.times(0.03)
                            } else {
                                comision = montoInicial.times(0.075)
                            }
                            println("Escogiste CARRECRIPTO, se te aplicará una comisión de $comision")
                        }


                    }

                    if (cuenta != null) {
                        if (cuenta.calcularAntiguedad() <= 3) {
                            cashback = cuenta.calcularCashback(montoInicial, 0.02)
                            println("Se te aplicará un cashback de $cashback")

                        } else if (cuenta.calcularAntiguedad() > 3 && cuenta.calcularAntiguedad() <= 12) {
                            cashback = cuenta.calcularCashback(montoInicial, 0.03)
                            println("Se te aplicará un cashback de $cashback")

                        }
                    }

                    println("¿Desea confirmar la compra? \n 1) si \n 2) no")
                    val confirmacion = readLine()?.toInt()

                    when (confirmacion) {
                        1 -> {
                            val precioFinalLambda = { monto: Double -> monto.plus(comision) }
                            val valorPagado = precioFinalLambda(montoInicial)
                            val valorAdquiridoLambda = { monto: Double -> monto.div(cripto.precio) }
                            val valorAdquirido = valorAdquiridoLambda(montoInicial)
                            if (cuenta != null) {
                                if (cuenta.verificarMonto(precioFinalLambda(montoInicial))) {
                                    cuenta.comprar(precioFinalLambda(montoInicial))
                                    cuenta.aplicarCashback(cashback)
                                } else {
                                    throw DineroInsuficienteException()
                                }
                                val compra = Compra(
                                    LocalDate.now(), LocalTime.now(), cripto, cantidad!!.toInt(),
                                    valorAdquirido, valorPagado, exchange2
                                )
                                println(compra.toString())
                                cuenta.agregarCompra(compra)
                            }
                        }

                        2 -> {
                            println("No se efectuará la compra")
                        }

                        else -> println("Opción incorrecta")

                    }


                }


                3 -> {
                    println(cuenta?.comprasRealizadas)
                }


            }


        } while (opcion != 4)


    }








