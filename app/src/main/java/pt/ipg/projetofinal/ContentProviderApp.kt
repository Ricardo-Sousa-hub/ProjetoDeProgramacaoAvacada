package pt.ipg.projetofinal

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri

class ContentProviderApp : ContentProvider() {
    var db : BDAppOpenHelper? = null

    override fun onCreate(): Boolean {
        db = BDAppOpenHelper(context)

        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        TODO("Not yet implemented")
    }

    override fun getType(uri: Uri): String? {
        TODO("Not yet implemented")
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        TODO("Not yet implemented")
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        TODO("Not yet implemented")
    }

    companion object {
        const val AUTHORITY = "pt.ipg.projetofinal"

        const val URI_UTILIZADOR = 100
        const val URI_UTILIZADOR_ESPECIFICO = 101
        const val URI_TIPO_COMBUSTIVEL = 200
        const val URI_TIPO_COMBUSTIVEL_ESPECIFICO = 201
        const val URI_MODELO = 300
        const val URI_MODELO_ESPECIFICO = 301
        const val URI_CARRO= 400
        const val URI_CARRO_ESPECIFICO = 401
        const val URI_TIPO_DESPESA = 500
        const val URI_TIPO_DESPESA_ESPECIFICO = 501
        const val URI_DESPESA = 600
        const val URI_DESPESA_ESPECIFICO = 601

        fun getUriMatcher() : UriMatcher {
            var uriMatcher = UriMatcher(UriMatcher.NO_MATCH)

            uriMatcher.addURI(AUTHORITY, TabelaBDUtilizadores.NOME_TABELA, URI_UTILIZADOR)
            uriMatcher.addURI(AUTHORITY, "${TabelaBDUtilizadores.NOME_TABELA}/#", URI_UTILIZADOR_ESPECIFICO)

            uriMatcher.addURI(AUTHORITY, TabelaBDTipoCombustivel.NOME_TABELA, URI_TIPO_COMBUSTIVEL)
            uriMatcher.addURI(AUTHORITY, "${TabelaBDTipoCombustivel.NOME_TABELA}/#", URI_TIPO_COMBUSTIVEL_ESPECIFICO)

            uriMatcher.addURI(AUTHORITY, TabelaBDModelos.NOME_TABELA, URI_MODELO)
            uriMatcher.addURI(AUTHORITY, "${TabelaBDModelos.NOME_TABELA}/#", URI_MODELO_ESPECIFICO)

            uriMatcher.addURI(AUTHORITY, TabelaBDCarros.NOME_TABELA, URI_CARRO)
            uriMatcher.addURI(AUTHORITY, "${TabelaBDCarros.NOME_TABELA}/#", URI_CARRO_ESPECIFICO)

            uriMatcher.addURI(AUTHORITY, TabelaBDTipoDespesa.NOME_TABELA, URI_TIPO_DESPESA)
            uriMatcher.addURI(AUTHORITY, "${TabelaBDTipoDespesa.NOME_TABELA}/#", URI_TIPO_DESPESA_ESPECIFICO)

            uriMatcher.addURI(AUTHORITY, TabelaBDDespesas.NOME_TABELA, URI_DESPESA)
            uriMatcher.addURI(AUTHORITY, "${TabelaBDDespesas.NOME_TABELA}/#", URI_DESPESA_ESPECIFICO)

            return uriMatcher
        }
    }
}