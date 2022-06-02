package pt.ipg.projetofinal

import android.content.ContentValues

data class Despesa(
    var id_tipo_despesa: Long,
    var data_despesa: String,
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

}