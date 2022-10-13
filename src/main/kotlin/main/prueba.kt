package main

import entidades.Usuario
import excepciones.LogueoIncorrectoException
import repositorios.CuentaRepositorio
import repositorios.UsuarioRepositorio

fun main(){
    val usuarioRepo = UsuarioRepositorio
    val cuentas = CuentaRepositorio
    var nickname=" "
    var password=" "
    var validacionAny=false

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
    println(cuenta!!.calcularAntiguedad())

}