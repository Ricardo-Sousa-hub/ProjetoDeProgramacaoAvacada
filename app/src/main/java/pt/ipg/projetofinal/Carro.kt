package pt.ipg.projetofinal

import android.content.ContentValues

data class Carro(
    var id: Long,
    var data_introduzida: String,
    var tipo_combustivel: String,
    var modelo: String,
    var id_utilizador: Long
    ) {

    fun toContentValues() : ContentValues{
        val valores = ContentValues()

        valores.put(TabelaBDCarros.DATA_INSERT, data_introduzida)
        valores.put(TabelaBDCarros.TIPO_COMBUSTIVEL, tipo_combustivel)
        valores.put(TabelaBDCarros.MODELO, modelo)
        valores.put(TabelaBDCarros.ID_UTILIZADOR, id_utilizador)

        return valores
    }
}