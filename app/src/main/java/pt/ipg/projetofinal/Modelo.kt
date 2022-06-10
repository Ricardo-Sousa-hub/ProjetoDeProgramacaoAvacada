package pt.ipg.projetofinal

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns

data class Modelo(
    var nome: String,
    var ano: Long,
    var id: Long = -1
) {

    fun toContentValues() : ContentValues{
        val valores = ContentValues()
        valores.put(TabelaBDModelos.NOME_MODELO, nome)
        valores.put(TabelaBDModelos.DATA, ano)

        return valores
    }

    companion object{
        fun fromCursor(cursor: Cursor): Modelo {
            val posId = cursor.getColumnIndex(BaseColumns._ID)
            val posNomeModelo = cursor.getColumnIndex(TabelaBDModelos.NOME_MODELO)
            val posData = cursor.getColumnIndex(TabelaBDModelos.DATA)

            val id = cursor.getLong(posId)
            val nome = cursor.getString(posNomeModelo)
            val data = cursor.getLong(posData)

            return Modelo(nome, data, id)
        }
    }

}