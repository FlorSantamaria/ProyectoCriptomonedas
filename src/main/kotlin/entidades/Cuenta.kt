package entidades

import java.time.LocalDate
import java.time.Period
import java.util.StringJoiner

class Cuenta(nickname:String, password:String, val codigoCuenta: Int, val nombre: String, val apellido: String, var dineroEnCuenta: Double, val fechaAlta: LocalDate):Usuario(nickname,password) {

    val comprasRealizadas= mutableListOf<Compra>()

    override fun toString()= "\"Codigo: $codigoCuenta, Nombre: $nombre" +
            "Apellido: $apellido, Dinero disponible: $dineroEnCuenta, FechaDeAlta: $fechaAlta"




    fun agregarCompra(compra: Compra){
        comprasRealizadas.add(compra)
    }

    fun mostrarCompras(): List<Compra>{
        return comprasRealizadas;
    }

    fun verificarMonto(monto:Double):Boolean{
       return monto<=dineroEnCuenta
    }

    fun calcularAntiguedad(): Int {
        val inicio=fechaAlta
        val fin=LocalDate.now()
        val period= Period.between(inicio,fin)
        return period.toTotalMonths().toInt()


    }

    fun comprar(valor:Double)  {
      dineroEnCuenta-=dineroEnCuenta
    }


    fun calcularCashback(monto:Double, porcentaje:Double): Double{
        val resultado=monto.times(porcentaje)
        return resultado;
    }
    fun aplicarCashback(monto:Double){
        dineroEnCuenta+=monto;
    }
}











