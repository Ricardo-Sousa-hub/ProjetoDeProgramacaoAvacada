package pt.ipg.projetofinal

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaBDTipoCombustivel(db: SQLiteDatabase) : TabelaBD(db, NOME_TABELA) {

    override fun cria(){
        db.execSQL("CREATE TABLE $nome (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$NOME_COMBUSTIVEL TEXT NOT NULL)")
    }

    companion object{
        const val NOME_TABELA = "tipo_combustivel"
        const val NOME_COMBUSTIVEL = "combustivel"
    }

}