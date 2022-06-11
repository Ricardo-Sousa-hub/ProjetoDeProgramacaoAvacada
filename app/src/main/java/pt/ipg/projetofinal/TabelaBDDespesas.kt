package pt.ipg.projetofinal

import android.database.sqlite.SQLiteDatabase
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

    companion object{
        const val NOME_TABELA = "despesas"
        const val ID_TIPO_DESPESA = "id_tipo_despesa"
        const val DATA_DESPESA = "data_despesa"
        const val VALOR_DESPESA = "valor_despesa"
        const val ID_CARRO = "id_carro"

        val TODAS_COLUNAS = arrayOf(
            BaseColumns._ID,
            ID_TIPO_DESPESA,
            DATA_DESPESA,
            VALOR_DESPESA,
            ID_CARRO
        )
    }

}