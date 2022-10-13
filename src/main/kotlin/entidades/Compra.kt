package entidades

import java.time.LocalDate
import java.time.LocalTime


 class Compra(val fechaCompra: LocalDate, val horaCompra: LocalTime,
              val criptomoneda: Bitcoin, val cantidad:Int, val valorAdquirido: Double,
              val valorPagado: Double,val exchange: Exchange,var codigoCompra:Int=0){

     init {
         codigoCompra++;
     }

   fun calcularPrecio():Double=criptomoneda.precio*cantidad

    fun calcularValorAdquiridoEnBitcoins():Double=valorPagado/criptomoneda.precio

     override fun toString ()= "-Còdigo: $codigoCompra \n -Fecha y hora: $fechaCompra $horaCompra - \n" +
                                "- Criptomoneda: $cantidad ${criptomoneda.nombre} " +
                                "\n -Valor Adquirido: $valorAdquirido \n -Valor Pagado: $valorPagado \n"

    fun calcularValorPagado(monto:Double,comision:Double,cashback: Double =0.0
    ):Double {
        val resultado=(monto+comision)-cashback
        return resultado;
    }



 }


