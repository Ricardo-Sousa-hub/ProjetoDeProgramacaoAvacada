package pt.ipg.projetofinal

import android.content.ContentValues

data class TipoDespesa(
    var nome: String,
    var id: Long = -1
) {

    fun toContentValues() : ContentValues{
        val valores = ContentValues()
        valores.put(TabelaBDTipoDespesa.NOME_DESPESA, nome)

        return valores
    }

}