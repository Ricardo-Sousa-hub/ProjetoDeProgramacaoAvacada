package pt.ipg.projetofinal

import android.content.ContentValues

data class Modelo(
    var nome: String,
    var ano: Long,
    var id: Long = -1
) {

    fun toContentValues() : ContentValues{
        val valores = ContentValues()
        valores.put(TabelaBDModelos.NOME_MODELO, nome)
        valores.put(TabelaBDModelos.ANO_MODELO, ano)

        return valores
    }

}