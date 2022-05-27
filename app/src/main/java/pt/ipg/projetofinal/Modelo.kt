package pt.ipg.projetofinal

import android.content.ContentValues

data class Modelo(
    var id: Long,
    var nome: String,
    var ano: String
) {

    fun toContentValues() : ContentValues{
        val valores = ContentValues()
        valores.put(TabelaBDModelos.NOME_MODELO, nome)
        valores.put(TabelaBDModelos.ANO_MODELO, ano)

        return valores
    }

}