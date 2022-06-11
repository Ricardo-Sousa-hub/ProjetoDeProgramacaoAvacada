package pt.ipg.projetofinal

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns

data class TipoDespesa(
    var nome: String,
    var id: Long = -1
) {

    fun toContentValues() : ContentValues{
        val valores = ContentValues()
        valores.put(TabelaBDTipoDespesa.NOME_DESPESA, nome)

        return valores
    }

    companion object{
        fun fromCursor(cursor: Cursor): TipoDespesa {
            val posId = cursor.getColumnIndex(BaseColumns._ID)
            val posNome = cursor.getColumnIndex(TabelaBDTipoDespesa.NOME_DESPESA)

            val id = cursor.getLong(posId)
            val nome = cursor.getString(posNome)

            return TipoDespesa(nome, id)
        }
    }

}