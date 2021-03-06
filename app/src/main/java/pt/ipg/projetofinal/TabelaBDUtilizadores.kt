package pt.ipg.projetofinal

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaBDUtilizadores(db: SQLiteDatabase) : TabelaBD(db, NOME_TABELA) {

    override fun cria(){
        db.execSQL("CREATE TABLE $nome (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$NOME TEXT NOT NULL)")
    }

    companion object{
        const val NOME_TABELA = "utilizadores"
        const val CAMPO_ID = "$NOME_TABELA.${BaseColumns._ID}"
        const val NOME = "nome"

        val TODAS_COLUNAS = arrayOf(
            CAMPO_ID,
            NOME
        )
    }

}