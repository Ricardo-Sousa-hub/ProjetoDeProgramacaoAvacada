package pt.ipg.projetofinal

import android.content.ContentValues

data class Carro(
    var data: Long,
    var id_tipo_combustivel: Long,
    var id_modelo: Long,
    var id_utilizador: Long,
    var id: Long = -1
    ) {

    fun toContentValues() : ContentValues{
        val valores = ContentValues()

        valores.put(TabelaBDCarros.DATA, data)
        valores.put(TabelaBDCarros.ID_TIPO_COMBUSTIVEL, id_tipo_combustivel)
        valores.put(TabelaBDCarros.ID_MODELO, id_modelo)
        valores.put(TabelaBDCarros.ID_UTILIZADOR, id_utilizador)

        return valores
    }
}