package pt.ipg.projetofinal

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaBDCarros(db:SQLiteDatabase) : TabelaBD(db, NOME_TABELA) {

    override fun cria(){
        db.execSQL("CREATE TABLE $nome (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$DATA_INSERT TEXT NOT NULL," +
                "$ID_TIPO_COMBUSTIVEL INTEGER NOT NULL," +
                "FOREIGN KEY $ID_TIPO_COMBUSTIVEL) REFERENCES ${TabelaBDTipoCombustivel.NOME_TABELA}(${BaseColumns._ID}) ON DELETE RESTRICT"+
                "$ID_MODELO INTEGER NOT NULL," +
                "FOREIGN KEY ($ID_MODELO) REFERENCES ${TabelaBDModelos.NOME_TABELA}(${BaseColumns._ID}) ON DELETE RESTRICT"+
                "$ID_UTILIZADOR INTEGER NOT NULL," +
                "FOREIGN KEY ($ID_UTILIZADOR) REFERENCES ${TabelaBDUtilizadores.NOME_TABELA}(${BaseColumns._ID}) ON DELETE RESTRICT)")
    }

    companion object{
        const val NOME_TABELA = "carros"
        const val DATA_INSERT = "data_introduzido"
        const val ID_TIPO_COMBUSTIVEL = "id_tipo_combustivel"
        const val ID_MODELO = "id_modelo"
        const val ID_UTILIZADOR = "id_utilizador"
    }

}