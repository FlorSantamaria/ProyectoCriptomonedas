package repositorios

import entidades.Compra
import entidades.Usuario
import java.time.LocalDate

object UsuarioRepositorio {
    val usuarios = mutableListOf<Usuario>()

    init {
        // Usuario (nickname,password)
        usuarios.add(Usuario("FlorSanta","112233"))
        usuarios.add(Usuario("Fabi","223344"))
        usuarios.add(Usuario("Luana","2650"))
        usuarios.add(Usuario("Mauro","2720"))
    }


    fun agregar(usuario: Usuario) {
        if(existe(usuario.nickname)){
            //TODO fallar
           null
        }
        usuarios.add(usuario)
    }

    fun eliminar(usuario: Usuario) {
        usuarios.remove(usuario)
    }

    fun existe(nickname: String): Boolean {
        for (usuario in usuarios) {
            if (usuario.nickname == nickname){
                return true;
            }
        }

        return false;
    }



   /* fun validacion(nickname: String, password: String): Boolean {
       for(usuario in usuarios) {
           if(usuario.nickname==nickname && usuario.password==password)
               return true;
       }
        return false;
    }
*/
    fun iniciar(nickname: String, password: String): Usuario? {
            for(usuario in usuarios){
                if(usuario.nickname==nickname&&usuario.password==password){
                    return usuario
                }
            }
        return null
    }
}


