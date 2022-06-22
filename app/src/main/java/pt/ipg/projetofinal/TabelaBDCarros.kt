package pt.ipg.projetofinal

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteQueryBuilder
import android.provider.BaseColumns
import androidx.navigation.ui.AppBarConfiguration

class TabelaBDCarros(db:SQLiteDatabase) : TabelaBD(db, NOME_TABELA) {

    override fun cria(){
        db.execSQL("CREATE TABLE $nome (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$DATA INTEGER NOT NULL," +
                "$ID_MODELO INTEGER NOT NULL," +
                "$ID_TIPO_COMBUSTIVEL INTEGER NOT NULL," +
                "$ID_UTILIZADOR INTEGER NOT NULL," +
                "FOREIGN KEY ($ID_MODELO) REFERENCES ${TabelaBDModelos.NOME_TABELA}(${BaseColumns._ID}) ON DELETE RESTRICT," +
                "FOREIGN KEY ($ID_TIPO_COMBUSTIVEL) REFERENCES ${TabelaBDTipoCombustivel.NOME_TABELA}(${BaseColumns._ID}) ON DELETE RESTRICT," +
                "FOREIGN KEY ($ID_UTILIZADOR) REFERENCES ${TabelaBDUtilizadores.NOME_TABELA}(${BaseColumns._ID}) ON DELETE RESTRICT)")
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
                " ${TabelaBDTipoCombustivel.NOME_TABELA}," +
                " ${TabelaBDModelos.NOME_TABELA}," +
                " ${TabelaBDUtilizadores.NOME_TABELA}" +
                " WHERE" +
                " ${TabelaBDTipoCombustivel.CAMPO_ID} = $ID_TIPO_COMBUSTIVEL" +
                " AND" +
                " ${TabelaBDModelos.CAMPO_ID} = $ID_MODELO" +
                " AND" +
                " ${TabelaBDUtilizadores.CAMPO_ID} = $ID_UTILIZADOR"

        return queryBuilder.query(db, columns, selection, selectionArgs, groupBy, having, orderBy)
    }

    companion object{
        const val NOME_TABELA = "carros"

        const val CAMPO_ID = "$NOME_TABELA.${BaseColumns._ID}"
        const val DATA = "data"
        const val ID_TIPO_COMBUSTIVEL = "id_tipo_combustivel"
        const val ID_MODELO = "id_modelo"
        const val ID_UTILIZADOR = "id_utilizador"

        val TODAS_COLUNAS = arrayOf(
            CAMPO_ID,
            DATA,
            TabelaBDTipoCombustivel.NOME_COMBUSTIVEL,
            TabelaBDModelos.NOME_MODELO,
            TabelaBDUtilizadores.NOME
        )
    }

}