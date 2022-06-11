package pt.ipg.projetofinal

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BDAppOpenHelper(
    context: Context?
) : SQLiteOpenHelper(context, NOME, null, VERSAO) {

    override fun onCreate(db: SQLiteDatabase?) {
        requireNotNull(db)// if(db == null) return

        TabelaBDUtilizadores(db).cria()
        TabelaBDTipoCombustivel(db).cria()
        TabelaBDModelos(db).cria()
        TabelaBDCarros(db).cria()
        TabelaBDTipoDespesa(db).cria()
        TabelaBDDespesas(db).cria()
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    companion object{
        const val NOME = "carros.db" //Nome da base de dados
        private const val VERSAO = 1
    }
}