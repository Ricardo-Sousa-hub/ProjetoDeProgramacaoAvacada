package pt.ipg.projetofinal

import android.content.ContentValues
import android.content.Intent
import android.database.Cursor
import android.provider.BaseColumns

data class Carro(
    var data: Long,
    var id_tipo_combustivel: Long,
    var modelo: Modelo,
    var utilizador: Utilizador,
    var id: Long = -1
    ) {

    fun toContentValues() : ContentValues{
        val valores = ContentValues()

        valores.put(TabelaBDCarros.DATA, data)
        valores.put(TabelaBDCarros.ID_TIPO_COMBUSTIVEL, id_tipo_combustivel)
        valores.put(TabelaBDCarros.ID_MODELO, modelo.id)
        valores.put(TabelaBDCarros.ID_UTILIZADOR, utilizador.id)

        return valores
    }

    companion object{
        fun fromCursor(cursor: Cursor): Carro {
            val posId = cursor.getColumnIndex(BaseColumns._ID)
            val posData = cursor.getColumnIndex(TabelaBDCarros.DATA)
            val posIdTipoCombusivel = cursor.getColumnIndex(TabelaBDCarros.ID_TIPO_COMBUSTIVEL)
            val posIdModelo = cursor.getColumnIndex(TabelaBDCarros.ID_MODELO)
            val posNomeModelo = cursor.getColumnIndex(TabelaBDModelos.NOME_MODELO)
            val posDataModelo = cursor.getColumnIndex(TabelaBDModelos.DATA)
            val posIdUtilizador = cursor.getColumnIndex(TabelaBDCarros.ID_UTILIZADOR)
            val posNomeUtilizador = cursor.getColumnIndex(TabelaBDUtilizadores.NOME)
            val posDataUtilizador = cursor.getColumnIndex(TabelaBDUtilizadores.DATA_NASCIMENTO)

            val id = cursor.getLong(posId)
            val data = cursor.getLong(posData)
            val idTipoCombustivel = cursor.getLong(posIdTipoCombusivel)
            val idModelo = cursor.getLong(posIdModelo)
            val nomeModelo = cursor.getString(posNomeModelo)
            val dataModelo = cursor.getLong(posDataModelo)
            val idUtilizador = cursor.getLong(posIdUtilizador)
            val nomeUtilizador = cursor.getString(posNomeUtilizador)
            val dataUtilizador = cursor.getLong(posDataUtilizador)

            val modelo = Modelo(nomeModelo, dataModelo, idModelo)

            val utilizador = Utilizador(nomeUtilizador, dataUtilizador, idUtilizador)

            return Carro(data, idTipoCombustivel, modelo, utilizador, id)
        }
    }
}