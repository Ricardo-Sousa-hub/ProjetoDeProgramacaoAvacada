package pt.ipg.projetofinal

import android.content.ContentValues

data class Combustivel(
    var id: Long,
    var nome: String
) {

    fun toContentValues() : ContentValues{
        val valores = ContentValues()
        valores.put(TabelaBDTipoCombustivel.NOME_COMBUSTIVEL, nome)

        return valores
    }

}