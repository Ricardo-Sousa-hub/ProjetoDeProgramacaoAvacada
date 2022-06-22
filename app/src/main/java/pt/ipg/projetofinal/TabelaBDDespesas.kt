package pt.ipg.projetofinal

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteQueryBuilder
import android.provider.BaseColumns

class TabelaBDDespesas(db:SQLiteDatabase) : TabelaBD(db, NOME_TABELA) {

    override fun cria(){
        db.execSQL("CREATE TABLE $nome (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$ID_TIPO_DESPESA INTEGER NOT NULL," +
                "$DATA_DESPESA INTEGER NOT NULL," +
                "$VALOR_DESPESA REAL NOT NULL," +
                "$ID_CARRO INTEGER NOT NULL," +
                "FOREIGN KEY ($ID_TIPO_DESPESA) REFERENCES ${TabelaBDTipoDespesa.NOME_TABELA}(${BaseColumns._ID}) ON DELETE RESTRICT," +
                "FOREIGN KEY ($ID_CARRO) REFERENCES ${TabelaBDCarros.NOME_TABELA}(${BaseColumns._ID}) ON DELETE RESTRICT)")
    }

    override fun query(
        columns: Array<String>,
        selection: String?,
        selectionArgs: Array<String>?,
        groupBy: String?,
        having: String?,
        orderBy: String?
    ): Cursor {
        val queryBuilder = SQLiteQueryBuilder()
        queryBuilder.tables = "$NOME_TABELA," +
                " ${TabelaBDTipoDespesa.NOME_TABELA}," +
                " ${TabelaBDCarros.NOME_TABELA}" +
                " WHERE" +
                " ${TabelaBDTipoDespesa.CAMPO_ID} = $ID_TIPO_DESPESA " +
                " AND" +
                " ${TabelaBDCarros.CAMPO_ID} = $ID_CARRO"


        return queryBuilder.query(db, columns, selection, selectionArgs, groupBy, having, orderBy)
    }

    companion object{
        const val NOME_TABELA = "despesas"
        const val CAMPO_ID = "$NOME_TABELA.${BaseColumns._ID}"
        const val ID_TIPO_DESPESA = "id_tipo_despesa"
        const val DATA_DESPESA = "data_despesa"
        const val VALOR_DESPESA = "valor_despesa"
        const val ID_CARRO = "id_carro"

        val TODAS_COLUNAS = arrayOf(
            CAMPO_ID,
            TabelaBDTipoDespesa.NOME_DESPESA,
            DATA_DESPESA,
            VALOR_DESPESA,
            TabelaBDCarros.ID_MODELO
        )
    }

}