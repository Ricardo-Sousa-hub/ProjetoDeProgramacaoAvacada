package pt.ipg.projetofinal

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaBDUtilizadores(db: SQLiteDatabase) : TabelaBD(db, NOME_TABELA) {

    override fun cria(){
        db.execSQL("CREATE TABLE $nome (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$NOME TEXT NOT NULL," +
                "$DATA_NASCIMENTO TEXT NOT NULL)")
    }

    companion object{
        const val NOME_TABELA = "utilizadores"
        const val NOME = "nome"
        const val DATA_NASCIMENTO = "data_nascimento"

        val TODAS_COLUNAS = arrayOf(
            BaseColumns._ID,
            NOME,
            DATA_NASCIMENTO
        )
    }

}