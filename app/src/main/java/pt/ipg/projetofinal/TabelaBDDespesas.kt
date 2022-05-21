package pt.ipg.projetofinal

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaBDDespesas(val db:SQLiteDatabase) {

    fun cria(){
        db.execSQL("CREATE TABLE $NOME_TABELA (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$TIPO_DESPESA TEXT NOT NULL," +
                "$DATA_DESPESA TEXT NOT NULL," +
                "$VALOR_DESPESA REAL NOT NULL," +
                "$ID_CARRO INTEGER NOT NULL," +
                "FOREIGN KEY ($ID_CARRO) REFERENCES ${TabelaBDCarros.NOME_TABELA}(${BaseColumns._ID}) ON DELETE RESTRICT)")
    }

    companion object{
        const val NOME_TABELA = "despesas"
        const val DATA_DESPESA = "data_despesa"
        const val VALOR_DESPESA = "valor_despesa"
        const val TIPO_DESPESA = "tipo_despesa"
        const val ID_CARRO = "id_carro"
    }

}