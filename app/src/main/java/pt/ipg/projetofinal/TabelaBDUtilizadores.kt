package pt.ipg.projetofinal

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaBDUtilizadores(val db: SQLiteDatabase) {

    fun cria(){
        db.execSQL("CREATE TABLE $NOME_TABELA (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$NOME TEXT NOT NULL," +
                "$DATA_NASCIMENTO TEXT NOT NULL)")
    }

    companion object{
        const val NOME_TABELA = "utilizadores"
        const val NOME = "nome"
        const val DATA_NASCIMENTO = "data_nascimento"
    }

}