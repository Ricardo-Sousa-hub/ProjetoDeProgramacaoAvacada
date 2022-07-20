package pt.ipg.projetofinal

import android.content.ContentValues
import android.content.Intent
import android.database.Cursor
import android.provider.BaseColumns
import java.io.Serializable

data class Carro(
    var data: Long,
    var combustivel: Combustivel,
    var modelo: Modelo,
    var utilizador: Utilizador,
    var id: Long = -1
    ) : Serializable {

    fun toContentValues() : ContentValues{
        val valores = ContentValues()

        valores.put(TabelaBDCarros.DATA, data)
        valores.put(TabelaBDCarros.ID_TIPO_COMBUSTIVEL, combustivel.id)
        valores.put(TabelaBDCarros.ID_MODELO, modelo.id)
        valores.put(TabelaBDCarros.ID_UTILIZADOR, utilizador.id)

        return valores
    }

    companion object{
        fun fromCursor(cursor: Cursor): Carro {
            val posId = cursor.getColumnIndex(BaseColumns._ID)
            val posData = cursor.getColumnIndex(TabelaBDCarros.DATA)

            val posIdTipoCombusivel = cursor.getColumnIndex(TabelaBDCarros.ID_TIPO_COMBUSTIVEL)
            val posNomeTipoCombustivel = cursor.getColumnIndex(TabelaBDTipoCombustivel.NOME_COMBUSTIVEL)

            val posIdModelo = cursor.getColumnIndex(TabelaBDCarros.ID_MODELO)
            val posNomeModelo = cursor.getColumnIndex(TabelaBDModelos.NOME_MODELO)
            val posDataModelo = cursor.getColumnIndex(TabelaBDModelos.DATA)

            val posIdUtilizador = cursor.getColumnIndex(TabelaBDCarros.ID_UTILIZADOR)
            val posNomeUtilizador = cursor.getColumnIndex(TabelaBDUtilizadores.NOME)

            val id = cursor.getLong(posId)
            val data = cursor.getLong(posData)

            val idTipoCombustivel = cursor.getLong(posIdTipoCombusivel)
            val nomeCombustivel = cursor.getString(posNomeTipoCombustivel)


            val idModelo = cursor.getLong(posIdModelo)
            val nomeModelo = cursor.getString(posNomeModelo)
            val dataModelo = cursor.getLong(posDataModelo)


            val idUtilizador = cursor.getLong(posIdUtilizador)
            val nomeUtilizador = cursor.getString(posNomeUtilizador)

            val combustivel = Combustivel(nomeCombustivel, idTipoCombustivel)
            val utilizador = Utilizador(nomeUtilizador, idUtilizador)
            val modelo = Modelo(nomeModelo, dataModelo, idModelo)

            return Carro(data, combustivel, modelo, utilizador, id)
        }
    }
}