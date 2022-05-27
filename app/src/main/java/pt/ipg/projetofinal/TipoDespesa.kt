package pt.ipg.projetofinal

import android.content.ContentValues

data class TipoDespesa(
    var id: Long,
    var nome: String
) {

    fun toContentValues() : ContentValues{
        val valores = ContentValues()
        valores.put(TabelaBDTipoDespesa.NOME_DESPESA, nome)

        return valores
    }

}