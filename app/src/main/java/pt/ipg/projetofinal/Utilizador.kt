package pt.ipg.projetofinal

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns
import java.io.Serializable

data class Utilizador(
    var nome: String,
    var id: Long = -1
    ) : Serializable {

    fun toContentValues() : ContentValues{
        val valores = ContentValues()
        valores.put(TabelaBDUtilizadores.NOME, nome)

        return valores
    }

    companion object{
        fun fromCursor(cursor: Cursor): Utilizador {
            val posId = cursor.getColumnIndex(BaseColumns._ID)
            val posNome = cursor.getColumnIndex(TabelaBDUtilizadores.NOME)

            val id = cursor.getLong(posId)
            val nome = cursor.getString(posNome)

            return Utilizador(nome, id)
        }
    }

}