package pt.ipg.projetofinal

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns

data class Combustivel(
    var nome: String,
    var id: Long = -1
    ) {

    fun toContentValues() : ContentValues{
        val valores = ContentValues()
        valores.put(TabelaBDTipoCombustivel.NOME_COMBUSTIVEL, nome)

        return valores
    }

    companion object{
        fun fromCursor(cursor: Cursor): Combustivel {
            val posId = cursor.getColumnIndex(BaseColumns._ID)
            val posNome = cursor.getColumnIndex(TabelaBDTipoCombustivel.NOME_COMBUSTIVEL)

            val id = cursor.getLong(posId)
            val nome_combustivel = cursor.getString(posNome)

            return Combustivel(nome_combustivel, id)
        }
    }

}