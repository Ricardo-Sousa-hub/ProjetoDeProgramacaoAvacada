package pt.ipg.projetofinal

import android.content.ContentValues

data class Despesa(
    var id: Long,
    var tipo_despesa: String,
    var data_despesa: String,
    var valor_despesa: Float,
    var id_carro: Long
    ) {

    fun toContentValues() : ContentValues{
        val valores = ContentValues()

        valores.put(TabelaBDDespesas.TIPO_DESPESA, tipo_despesa)
        valores.put(TabelaBDDespesas.DATA_DESPESA, data_despesa)
        valores.put(TabelaBDDespesas.VALOR_DESPESA, valor_despesa)
        valores.put(TabelaBDDespesas.ID_CARRO, id_carro)

        return valores
    }

}