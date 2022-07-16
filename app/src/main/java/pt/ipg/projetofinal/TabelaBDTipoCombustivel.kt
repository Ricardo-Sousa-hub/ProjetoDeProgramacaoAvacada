package pt.ipg.projetofinal

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaBDTipoCombustivel(db: SQLiteDatabase) : TabelaBD(db, NOME_TABELA) {

    override fun cria(){
        db.execSQL("CREATE TABLE $nome (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$NOME_COMBUSTIVEL TEXT NOT NULL)")
    }

    fun delete(){
        db.execSQL("DELETE FROM $NOME_TABELA")
    }

    companion object{
        const val NOME_TABELA = "tipo_combustivel"
        const val CAMPO_ID = "${TabelaBDTipoCombustivel.NOME_TABELA}.${BaseColumns._ID}"
        const val NOME_COMBUSTIVEL = "combustivel"

        val TODAS_COLUNAS = arrayOf(
            CAMPO_ID,
            NOME_COMBUSTIVEL
        )
    }

}