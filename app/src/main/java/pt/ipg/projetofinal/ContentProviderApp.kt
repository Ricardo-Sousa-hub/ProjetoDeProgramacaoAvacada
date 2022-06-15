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

    override fun getType(uri: Uri): String? =
        when(getUriMatcher().match(uri)){
            URI_UTILIZADOR -> "$MULTIPLOS_REGISTOS/${TabelaBDUtilizadores.NOME_TABELA}"
            URI_UTILIZADOR_ESPECIFICO -> "$UNICO_REGISTO/${TabelaBDUtilizadores.NOME_TABELA}"
            URI_TIPO_COMBUSTIVEL -> "$MULTIPLOS_REGISTOS/${TabelaBDTipoCombustivel.NOME_TABELA}"
            URI_TIPO_COMBUSTIVEL_ESPECIFICO -> "$UNICO_REGISTO/${TabelaBDTipoCombustivel.NOME_TABELA}"
            URI_MODELO -> "$MULTIPLOS_REGISTOS/${TabelaBDModelos.NOME_TABELA}"
            URI_MODELO_ESPECIFICO -> "$UNICO_REGISTO/${TabelaBDModelos.NOME_TABELA}"
            URI_CARRO -> "$MULTIPLOS_REGISTOS/${TabelaBDCarros.NOME_TABELA}"
            URI_CARRO_ESPECIFICO -> "$UNICO_REGISTO/${TabelaBDCarros.NOME_TABELA}"
            URI_TIPO_DESPESA -> "$MULTIPLOS_REGISTOS/${TabelaBDTipoDespesa.NOME_TABELA}"
            URI_TIPO_DESPESA_ESPECIFICO -> "$UNICO_REGISTO/${TabelaBDTipoDespesa.NOME_TABELA}"
            URI_DESPESA -> "$MULTIPLOS_REGISTOS/${TabelaBDDespesas.NOME_TABELA}"
            URI_DESPESA_ESPECIFICO -> "$UNICO_REGISTO/${TabelaBDDespesas.NOME_TABELA}"
            else -> null
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

        const val UNICO_REGISTO = "vnd.android.cursor.item"
        const val MULTIPLOS_REGISTOS = "vnd.android.cursor.dir"

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