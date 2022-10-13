package repositorios

import entidades.Cuenta
import entidades.Usuario
import repositorios.UsuarioRepositorio.iniciar
import repositorios.UsuarioRepositorio.usuarios
import java.time.LocalDate

object CuentaRepositorio {
    val cuentas = mutableListOf<Cuenta>()
    init {
        // Usuario (nickname,password)
        cuentas.add(Cuenta("FlorSanta","112233",1,"Florencia","Santamarìa",200.0,LocalDate.parse("2022-01-10")))
        cuentas.add(Cuenta("Fabi","223344",2,"Fabian","Schneiders ",15000.0,LocalDate.parse("2022-08-10")))
        cuentas.add(Cuenta("Luana","2650",3,"Luana","Vilacagua",9000.0,LocalDate.parse("2020-01-10")))
        cuentas.add(Cuenta("Mauro","2720",4,"Mauro","Catrambone",7700.0,LocalDate.parse("2020-01-10")))
    }

    fun agregar(cuenta: Cuenta) {
        cuentas.add(cuenta)
    }

    fun eliminar(cuenta: Cuenta) {
        cuentas.remove(cuenta)
    }

    fun obtenerPorCodigo(codigoCuenta:Int): Cuenta? {
       for(cuenta in cuentas){
           if(codigoCuenta==cuenta.codigoCuenta){
               return cuenta;
           }
       }
        return null
    }
    fun buscarCuentaPertenecienteAlUsuario(nickname:String):Cuenta?{
        for (cuenta in cuentas){
            for (usuario in usuarios){
                if(cuenta.nickname==nickname && usuario.nickname==nickname && cuenta.nickname==usuario.nickname){
                    return obtenerPorCodigo(cuenta.codigoCuenta);
                }
            }
        }
        return null;
    }


    fun buscar(nombre: String, apellido: String): Cuenta? {
        for(cuenta in cuentas){
            if(cuenta.nombre==nombre &&cuenta.apellido==apellido)
                return cuenta;
        }
        return null;
    }
}