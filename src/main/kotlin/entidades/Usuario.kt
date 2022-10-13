package entidades

import java.time.LocalDate


open class Usuario(val nickname:String,
                   val password:String) {


    override fun toString() = "Nickname: $nickname, Password: $password"



}