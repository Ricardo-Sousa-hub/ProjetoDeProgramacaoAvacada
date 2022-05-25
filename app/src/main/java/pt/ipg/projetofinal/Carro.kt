package pt.ipg.projetofinal

import android.content.ContentValues

data class Carro(
    var id: Long,
    var data_introduzida: String,
    var tipo_combustivel: String,
    var modelo: String,
    var id_utilizador: Long
    ) {

}