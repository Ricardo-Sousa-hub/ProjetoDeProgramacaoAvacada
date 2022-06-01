package pt.ipg.projetofinal

import android.content.ContentValues

data class Combustivel(
    var nome: String,
    var id: Long = -1
) {

    fun toContentValues() : ContentValues{
        val valores = ContentValues()
        valores.put(TabelaBDTipoCombustivel.NOME_COMBUSTIVEL, nome)

        return valores
    }

}