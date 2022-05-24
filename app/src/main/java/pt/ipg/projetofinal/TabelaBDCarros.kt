package pt.ipg.projetofinal

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaBDCarros(val db:SQLiteDatabase) {

    fun cria(){
        db.execSQL("CREATE TABLE $NOME_TABELA (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$DATA_INSERT TEXT NOT NULL," +
                "$TIPO_COMBUSTIVEL TEXT NOT NULL," +
                "$MODELO TEXT NOT NULL," +
                "$ID_UTILIZADOR INTEGER NOT NULL," +
                "FOREIGN KEY ($ID_UTILIZADOR) REFERENCES ${TabelaBDUtilizadores.NOME_TABELA}(${BaseColumns._ID}) ON DELETE RESTRICT)")
    }

    companion object{
        const val NOME_TABELA = "carros"
        const val DATA_INSERT = "data_introduzido"
        const val TIPO_COMBUSTIVEL = "tipo_combustivel"
        const val MODELO = "modelo"
        const val ID_UTILIZADOR = "id_utilizador"
    }

}