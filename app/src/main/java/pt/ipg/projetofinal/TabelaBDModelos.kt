package pt.ipg.projetofinal

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaBDModelos(db: SQLiteDatabase) : TabelaBD(db, NOME_TABELA) {

    override fun cria(){
        db.execSQL("CREATE TABLE $nome (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$NOME_MODELO TEXT NOT NULL," +
                "$DATA INTEGER NOT NULL)")
    }

    companion object{
        const val NOME_TABELA = "modelos"
        const val CAMPO_ID = "$NOME_TABELA.${BaseColumns._ID}"
        const val NOME_MODELO = "nome_modelo"
        const val DATA = "data_lancamento"

        val TODAS_COLUNAS = arrayOf(
            CAMPO_ID,
            NOME_MODELO,
            DATA
        )
    }

}