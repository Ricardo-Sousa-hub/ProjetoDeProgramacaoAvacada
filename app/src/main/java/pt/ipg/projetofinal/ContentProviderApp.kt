package pt.ipg.projetofinal

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import android.provider.BaseColumns

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
        val db = db!!.readableDatabase

        requireNotNull(projection)
        val colunas = projection as Array<String>

        val argsSelecao = selectionArgs as Array<String>?

        val id = uri.lastPathSegment

        val cursor = when (getUriMatcher().match(uri)){
            URI_UTILIZADOR -> TabelaBDUtilizadores(db).query(colunas, selection, argsSelecao, null, null, sortOrder)
            URI_UTILIZADOR_ESPECIFICO -> TabelaBDUtilizadores(db).query(colunas, "${BaseColumns._ID}=?", arrayOf("${id}"), null, null, null)
            URI_TIPO_COMBUSTIVEL -> TabelaBDTipoCombustivel(db).query(colunas, selection, argsSelecao, null, null, sortOrder)
            URI_TIPO_COMBUSTIVEL_ESPECIFICO -> TabelaBDTipoCombustivel(db).query(colunas, "${BaseColumns._ID}=?", arrayOf("${id}"), null, null, null)
            URI_MODELO -> TabelaBDModelos(db).query(colunas, selection, argsSelecao, null, null, sortOrder)
            URI_MODELO_ESPECIFICO -> TabelaBDModelos(db).query(colunas, "${BaseColumns._ID}=?", arrayOf("${id}"), null, null, null)
            URI_CARRO -> TabelaBDCarros(db).query(colunas, selection, argsSelecao, null, null, sortOrder)
            URI_CARRO_ESPECIFICO -> TabelaBDCarros(db).query(colunas, "${BaseColumns._ID}=?", arrayOf("${id}"), null, null, null)
            URI_TIPO_DESPESA -> TabelaBDTipoDespesa(db).query(colunas, selection, argsSelecao, null, null, sortOrder)
            URI_TIPO_DESPESA_ESPECIFICO -> TabelaBDTipoDespesa(db).query(colunas, "${BaseColumns._ID}=?", arrayOf("${id}"), null, null, null)
            URI_DESPESA -> TabelaBDDespesas(db).query(colunas, selection, argsSelecao, null, null, sortOrder)
            URI_DESPESA_ESPECIFICO -> TabelaBDDespesas(db).query(colunas, "${BaseColumns._ID}=?", arrayOf("${id}"), null, null, null)
            else -> null
        }

        return cursor
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
        val db = db!!.writableDatabase

        requireNotNull(values)

        val id = when(getUriMatcher().match(uri)){
            URI_UTILIZADOR -> TabelaBDUtilizadores(db).insert(values)
            URI_TIPO_COMBUSTIVEL -> TabelaBDTipoCombustivel(db).insert(values)
            URI_MODELO -> TabelaBDModelos(db).insert(values)
            URI_CARRO -> TabelaBDCarros(db).insert(values)
            URI_TIPO_DESPESA -> TabelaBDTipoDespesa(db).insert(values)
            URI_DESPESA -> TabelaBDDespesas(db).insert(values)
            else -> -1
        }

        db.close()

        if(id == -1L) return null

        return Uri.withAppendedPath(uri, "$id")
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        val db = db!!.writableDatabase

        val id = uri.lastPathSegment

        val registosApagados =  when(getUriMatcher().match(uri)){
            URI_UTILIZADOR_ESPECIFICO -> TabelaBDUtilizadores(db).delete("${BaseColumns._ID}=?", arrayOf("${id}"))
            URI_TIPO_COMBUSTIVEL_ESPECIFICO -> TabelaBDTipoCombustivel(db).delete("${BaseColumns._ID}=?", arrayOf("${id}"))
            URI_MODELO_ESPECIFICO -> TabelaBDModelos(db).delete("${BaseColumns._ID}=?", arrayOf("${id}"))
            URI_CARRO_ESPECIFICO -> TabelaBDCarros(db).delete("${BaseColumns._ID}=?", arrayOf("${id}"))
            URI_TIPO_DESPESA_ESPECIFICO -> TabelaBDTipoDespesa(db).delete("${BaseColumns._ID}=?", arrayOf("${id}"))
            URI_DESPESA_ESPECIFICO -> TabelaBDDespesas(db).delete("${BaseColumns._ID}=?", arrayOf("${id}"))
            else -> 0
        }

        db.close()

        return registosApagados

    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        requireNotNull(values)

        val db = db!!.writableDatabase

        val id = uri.lastPathSegment

        val registosAlterados = when(getUriMatcher().match(uri)){
            URI_UTILIZADOR_ESPECIFICO -> TabelaBDUtilizadores(db).update(values, "${BaseColumns._ID}=?", arrayOf("${id}"))
            URI_TIPO_COMBUSTIVEL_ESPECIFICO -> TabelaBDTipoCombustivel(db).update(values, "${BaseColumns._ID}=?", arrayOf("${id}"))
            URI_MODELO_ESPECIFICO -> TabelaBDModelos(db).update(values, "${BaseColumns._ID}=?", arrayOf("${id}"))
            URI_CARRO_ESPECIFICO -> TabelaBDCarros(db).update(values, "${BaseColumns._ID}=?", arrayOf("${id}"))
            URI_TIPO_DESPESA_ESPECIFICO -> TabelaBDTipoDespesa(db).update(values, "${BaseColumns._ID}=?", arrayOf("${id}"))
            URI_DESPESA_ESPECIFICO -> TabelaBDDespesas(db).update(values, "${BaseColumns._ID}=?", arrayOf("${id}"))
            else -> 0
        }

        db.close()

        return registosAlterados
    }

    companion object {
        private const val AUTHORITY = "pt.ipg.projetofinal"

        private const val URI_UTILIZADOR = 100
        private const val URI_UTILIZADOR_ESPECIFICO = 101
        private const val URI_TIPO_COMBUSTIVEL = 200
        private const val URI_TIPO_COMBUSTIVEL_ESPECIFICO = 201
        private const val URI_MODELO = 300
        private const val URI_MODELO_ESPECIFICO = 301
        private const val URI_CARRO= 400
        private const val URI_CARRO_ESPECIFICO = 401
        private const val URI_TIPO_DESPESA = 500
        private const val URI_TIPO_DESPESA_ESPECIFICO = 501
        private const val URI_DESPESA = 600
        private const val URI_DESPESA_ESPECIFICO = 601

        private const val UNICO_REGISTO = "vnd.android.cursor.item"
        private const val MULTIPLOS_REGISTOS = "vnd.android.cursor.dir"

        private val ENDERECO_BASE =Uri.parse("content://$AUTHORITY")
        val ENDERECO_UTILIZADOR =Uri.withAppendedPath(ENDERECO_BASE, TabelaBDUtilizadores.NOME_TABELA)
        val ENDERECO_TIPO_COMBUSTIVEL =Uri.withAppendedPath(ENDERECO_BASE, TabelaBDTipoCombustivel.NOME_TABELA) // adicionado pelo admin
        val ENDERECO_MODELO =Uri.withAppendedPath(ENDERECO_BASE, TabelaBDModelos.NOME_TABELA) // adicionado pelo admin
        val ENDERECO_CARROS =Uri.withAppendedPath(ENDERECO_BASE, TabelaBDCarros.NOME_TABELA)
        val ENDERECO_TIPO_DESPESA =Uri.withAppendedPath(ENDERECO_BASE, TabelaBDTipoDespesa.NOME_TABELA)
        val ENDERECO_DESPESAS =Uri.withAppendedPath(ENDERECO_BASE, TabelaBDDespesas.NOME_TABELA)

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