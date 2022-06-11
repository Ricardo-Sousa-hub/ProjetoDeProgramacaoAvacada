package pt.ipg.projetofinal

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns

data class Despesa(
    var id_tipo_despesa: Long,
    var data_despesa: Long,
    var valor_despesa: Float,
    var id_carro: Long,
    var id: Long = -1
    ) {

    fun toContentValues() : ContentValues{
        val valores = ContentValues()

        valores.put(TabelaBDDespesas.ID_TIPO_DESPESA, id_tipo_despesa)
        valores.put(TabelaBDDespesas.DATA_DESPESA, data_despesa)
        valores.put(TabelaBDDespesas.VALOR_DESPESA, valor_despesa)
        valores.put(TabelaBDDespesas.ID_CARRO, id_carro)

        return valores
    }

    companion object{
        fun fromCursor(cursor: Cursor): Despesa {
            val posId = cursor.getColumnIndex(BaseColumns._ID)
            val posIdTipoDespesa = cursor.getColumnIndex(TabelaBDDespesas.ID_TIPO_DESPESA)
            val posData = cursor.getColumnIndex(TabelaBDDespesas.DATA_DESPESA)
            val posValorDespesa = cursor.getColumnIndex(TabelaBDDespesas.VALOR_DESPESA)
            val posIdCarro = cursor.getColumnIndex(TabelaBDDespesas.ID_CARRO)

            val id = cursor.getLong(posId)
            val idTipoDespesa = cursor.getLong(posIdTipoDespesa)
            val data = cursor.getLong(posData)
            val valorDespesa = cursor.getFloat(posValorDespesa)
            val idCarro = cursor.getLong(posIdCarro)

            return Despesa(idTipoDespesa, data, valorDespesa, idCarro, id)
        }
    }

}