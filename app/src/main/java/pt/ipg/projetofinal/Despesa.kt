package pt.ipg.projetofinal

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns

data class Despesa(
    var tipo_despesa: TipoDespesa,
    var data_despesa: Long,
    var valor_despesa: Float,
    var carro: Carro,
    var id: Long = -1
    ) {

    fun toContentValues() : ContentValues{
        val valores = ContentValues()

        valores.put(TabelaBDDespesas.ID_TIPO_DESPESA, tipo_despesa.id)
        valores.put(TabelaBDDespesas.DATA_DESPESA, data_despesa)
        valores.put(TabelaBDDespesas.VALOR_DESPESA, valor_despesa)
        valores.put(TabelaBDDespesas.ID_CARRO, carro.id)

        return valores
    }

    companion object{
        fun fromCursor(cursor: Cursor): Despesa {
            val posId = cursor.getColumnIndex(BaseColumns._ID)
            val posIdTipoDespesa = cursor.getColumnIndex(TabelaBDDespesas.ID_TIPO_DESPESA)
            val posNomeTipoDespesa = cursor.getColumnIndex(TabelaBDTipoDespesa.NOME_DESPESA)

            val posData = cursor.getColumnIndex(TabelaBDDespesas.DATA_DESPESA)
            val posValorDespesa = cursor.getColumnIndex(TabelaBDDespesas.VALOR_DESPESA)
            val posIdCarro = cursor.getColumnIndex(TabelaBDDespesas.ID_CARRO)
            val posDataCarro = cursor.getColumnIndex(TabelaBDCarros.DATA)
            val posIdCombustivel = cursor.getColumnIndex(TabelaBDCarros.ID_TIPO_COMBUSTIVEL)
            val posIdModelo = cursor.getColumnIndex(TabelaBDCarros.ID_MODELO)
            val posIdUtilizador = cursor.getColumnIndex(TabelaBDCarros.ID_UTILIZADOR)

            val id = cursor.getLong(posId)
            val idTipoDespesa = cursor.getLong(posIdTipoDespesa)
            val nomeTipoDespesa = cursor.getString(posNomeTipoDespesa)
            val data = cursor.getLong(posData)
            val valorDespesa = cursor.getFloat(posValorDespesa)

            val idCarro = cursor.getLong(posIdCarro)
            val dataCarro = cursor.getLong(posDataCarro)
            val idCombustivel = cursor.getLong(posIdCombustivel)
            val idModelo = cursor.getLong(posIdModelo)
            val idUtilizador = cursor.getLong(posIdUtilizador)

            val tipoDespesa = TipoDespesa(nomeTipoDespesa, idTipoDespesa)

            val posNomeModelo = cursor.getColumnIndex(TabelaBDModelos.NOME_MODELO)
            val nomeModelo = cursor.getString(posNomeModelo)
            val modeloCarro = Modelo(nomeModelo, idModelo)

            val posNomeUtilizador = cursor.getColumnIndex(TabelaBDUtilizadores.NOME)
            val posDataUtilizador = cursor.getColumnIndex(TabelaBDUtilizadores.DATA_NASCIMENTO)
            val nomeUtilizador = cursor.getString(posNomeUtilizador)
            val dataUtilizador = cursor.getLong(posDataUtilizador)
            val utilizador = Utilizador(nomeUtilizador, dataUtilizador, idUtilizador)

            val carro = Carro(dataCarro, idCombustivel, modeloCarro, utilizador, idCarro)

            return Despesa(tipoDespesa, data, valorDespesa, carro, id)
        }
    }

}