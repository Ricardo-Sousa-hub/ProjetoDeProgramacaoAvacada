package pt.ipg.projetofinal

import android.content.ContentValues

data class Utilizador(
    var id: Long,
    var nome: String,
    var data_nascimento: String
    ) {

    fun toContentValues() : ContentValues{
        val valores = ContentValues()
        valores.put(TabelaBDUtilizadores.NOME, nome)
        valores.put(TabelaBDUtilizadores.DATA_NASCIMENTO, data_nascimento)

        return valores
    }

}