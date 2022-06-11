package pt.ipg.projetofinal

import android.content.ContentValues
import android.content.Intent
import android.database.Cursor
import android.provider.BaseColumns

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

    companion object{
        fun fromCursor(cursor: Cursor): Carro {
            val posId = cursor.getColumnIndex(BaseColumns._ID)
            val posData = cursor.getColumnIndex(TabelaBDCarros.DATA)
            val posIdTipoCombusivel = cursor.getColumnIndex(TabelaBDCarros.ID_TIPO_COMBUSTIVEL)
            val posIdModelo = cursor.getColumnIndex(TabelaBDCarros.ID_MODELO)
            val posIdUtilizador = cursor.getColumnIndex(TabelaBDCarros.ID_UTILIZADOR)

            val id = cursor.getLong(posId)
            val data = cursor.getLong(posData)
            val idTipoCombustivel = cursor.getLong(posIdTipoCombusivel)
            val idModelo = cursor.getLong(posIdModelo)
            val idUtilizador = cursor.getLong(posIdUtilizador)

            return Carro(data, idTipoCombustivel, idModelo, idUtilizador, id)
        }
    }
}