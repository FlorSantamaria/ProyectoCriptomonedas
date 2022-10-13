package repositorios

import entidades.Compra

object CompraRepositorio {
     val compra = mutableListOf<Compra>()

    fun agregar(compra: Compra) {
        this.compra.add(compra)
    }

    fun eliminar(compra: Compra) {
        this.compra.remove(compra)
    }

    fun obtenerPorCodigo(codigoCompra:Int): Compra ?{
        //TODO: Completar
        for(compra in compra){
            if(compra.codigoCompra==codigoCompra)
                return compra;
        }
        return null;
    }


    //TODO: elegir los datos para buscar
    fun buscar(codigoCompra:Int): List<Compra> {
        //TODO: Completar
        return compra
    }
}