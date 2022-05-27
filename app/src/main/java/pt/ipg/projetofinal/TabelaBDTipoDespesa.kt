package pt.ipg.projetofinal

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaBDTipoDespesa(db: SQLiteDatabase) : TabelaBD(db, NOME_TABELA) {

    override fun cria(){
        db.execSQL("CREATE TABLE $nome (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$NOME_DESPESA TEXT NOT NULL)")
    }

    companion object{
        const val NOME_TABELA = "tipo_despesa"
        const val NOME_DESPESA = "nome_despesa"
    }

}