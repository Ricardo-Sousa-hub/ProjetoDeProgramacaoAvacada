package pt.ipg.projetofinal

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns

data class Utilizador(
    var nome: String,
    var data_nascimento: Long,
    var id: Long = -1
    ) {

    fun toContentValues() : ContentValues{
        val valores = ContentValues()
        valores.put(TabelaBDUtilizadores.NOME, nome)
        valores.put(TabelaBDUtilizadores.DATA_NASCIMENTO, data_nascimento)

        return valores
    }

    companion object{
        fun fromCursor(cursor: Cursor): Utilizador {
            val posId = cursor.getColumnIndex(BaseColumns._ID)
            val posNome = cursor.getColumnIndex(TabelaBDUtilizadores.NOME)
            val posData = cursor.getColumnIndex(TabelaBDUtilizadores.DATA_NASCIMENTO)

            val id = cursor.getLong(posId)
            val nome = cursor.getString(posNome)
            val data = cursor.getLong(posData)

            return Utilizador(nome, data, id)
        }
    }

}