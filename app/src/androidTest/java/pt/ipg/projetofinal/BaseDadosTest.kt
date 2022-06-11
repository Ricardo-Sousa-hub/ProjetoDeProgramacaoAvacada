package pt.ipg.projetofinal

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
import android.view.Display
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class BaseDadosTest {
    fun appContext() = InstrumentationRegistry.getInstrumentation().targetContext

    private fun getWritableDatabase(): SQLiteDatabase {
        val openHelper = BDAppOpenHelper(appContext())
        return openHelper.writableDatabase
    }

    private fun insereUtilizador(db: SQLiteDatabase, utilizador: Utilizador) {
        utilizador.id = TabelaBDUtilizadores(db).insert(utilizador.toContentValues())
        assertNotEquals(-1, utilizador.id)
    }

    private fun insereCombustivel(db: SQLiteDatabase, combustivel: Combustivel) {
        combustivel.id = TabelaBDTipoCombustivel(db).insert(combustivel.toContentValues())
        assertNotEquals(-1, combustivel.id)
    }

    private fun insereModelo(db: SQLiteDatabase, modelo: Modelo) {
        modelo.id = TabelaBDModelos(db).insert(modelo.toContentValues())
        assertNotEquals(-1, modelo.id)
    }

    private fun insereCarro(db: SQLiteDatabase, carro: Carro) {
        carro.id = TabelaBDCarros(db).insert(carro.toContentValues())
        assertNotEquals(-1, carro.id)
    }

    private fun insereTipoDespesa(db: SQLiteDatabase, tipoDespesa: TipoDespesa) {
        tipoDespesa.id = TabelaBDTipoDespesa(db).insert(tipoDespesa.toContentValues())
        assertNotEquals(-1, tipoDespesa.id)
    }

    private fun insereDespesa(db: SQLiteDatabase, despesa: Despesa){
        despesa.id = TabelaBDDespesas(db).insert(despesa.toContentValues())
        assertNotEquals(-1, despesa.id)
    }

    @Before
    fun apagarBaseDados(){
        appContext().deleteDatabase(BDAppOpenHelper.NOME)
    }

    @Test
    fun consegueAbrirBaseDados(){
        val openHelper = BDAppOpenHelper(appContext())
        val db = openHelper.readableDatabase

        assertTrue(db.isOpen)

        db.close()
    }

    @Test
    fun consegueInserirUtilizador(){
        val db = getWritableDatabase()

        insereUtilizador(db, Utilizador("Ricardo Sousa", 20020625))

        db.close()
    }

    @Test
    fun consegueAlterarUtilizador(){
        val db = getWritableDatabase()

        val utilizador = Utilizador("Ricardo Sousa", 20020625)

        insereUtilizador(db, utilizador)

        utilizador.nome = "Joao"
        utilizador.data_nascimento = 20020328

        val registoAlterado = TabelaBDUtilizadores(db).update(
            utilizador.toContentValues(),
            "${BaseColumns._ID}=?",
            arrayOf("${utilizador.id}")
        )

        assertEquals(1, registoAlterado)

        db.close()
    }

    @Test
    fun consegueInserirCombustivel(){
        val db = getWritableDatabase()

        insereCombustivel(db, Combustivel("Gasoleo"))

        db.close()
    }

    @Test
    fun consegueAlterarCombustivel(){
        val db = getWritableDatabase()

        val combustivel = Combustivel("Gasoleo")

        insereCombustivel(db, combustivel)

        combustivel.nome = "Gasolina"

        val registoAlterado = TabelaBDTipoCombustivel(db).update(
            combustivel.toContentValues(),
            "${BaseColumns._ID}=?",
            arrayOf("${combustivel.id}")
        )

        assertEquals(1, registoAlterado)

        db.close()
    }

    @Test
    fun consegueInserirModelo(){
        val db = getWritableDatabase()

        insereModelo(db, Modelo("BMW X6", 2020))

        db.close()
    }

    @Test
    fun consegueAlterarModelo(){
        val db = getWritableDatabase()

        val modelo = Modelo("BMW X6", 2020)

        insereModelo(db, modelo)

        modelo.nome = "Mercedes"
        modelo.data = 20022506

        val registoAlterado = TabelaBDModelos(db).update(
            modelo.toContentValues(),
            "${BaseColumns._ID}=?",
            arrayOf("${modelo.id}")
        )

        assertEquals(1, registoAlterado)

        db.close()
    }

    @Test
    fun consegueInserirCarro(){
        val db = getWritableDatabase()

        val utilizador = Utilizador("Ricardo Sousa", 20020625)
        insereUtilizador(db, utilizador)

        val combustivel = Combustivel("Gasoleo")
        insereCombustivel(db, combustivel)

        val modelo = Modelo("BMW X6", 2020)
        insereModelo(db, modelo)

        val carro = Carro(2020, combustivel.id, modelo.id, utilizador.id)
        insereCarro(db, carro)

        db.close()

    }

    @Test
    fun consegueAlterarCarro(){

        val db = getWritableDatabase()

        val utilizador = Utilizador("Ricardo Sousa", 20020625)
        insereUtilizador(db, utilizador)

        val combustivel = Combustivel("Gasoleo")
        insereCombustivel(db, combustivel)

        val modelo = Modelo("BMW X6", 2020)
        insereModelo(db, modelo)

        val carro = Carro(2020, combustivel.id, modelo.id, utilizador.id)
        insereCarro(db, carro)

        carro.data = 1999

        val registoAlterado = TabelaBDCarros(db).update(
            carro.toContentValues(),
            "${BaseColumns._ID}=?",
            arrayOf("${carro.id}")
        )

        assertEquals(1, registoAlterado)

        db.close()
    }

    @Test
    fun consegueInserirTipoDespesa(){
        val db = getWritableDatabase()

        insereTipoDespesa(db, TipoDespesa("Combustivel"))

        db.close()
    }

    @Test
    fun consegueAlterarTipoDespesa(){
        val db = getWritableDatabase()

        val tipoDespesa = TipoDespesa("Portagens")

        insereTipoDespesa(db, tipoDespesa)

        tipoDespesa.nome = "Combustivel"

        val registoAlterado = TabelaBDTipoDespesa(db).update(
            tipoDespesa.toContentValues(),
            "${BaseColumns._ID}=?",
            arrayOf("${tipoDespesa.id}")
        )

        assertEquals(1, registoAlterado)

        db.close()
    }

    @Test
    fun consegueInserirDespesa(){
        val db = getWritableDatabase()

        val utilizador = Utilizador("Ricardo Sousa", 20020625)
        insereUtilizador(db, utilizador)

        val combustivel = Combustivel("Gasoleo")
        insereCombustivel(db, combustivel)

        val modelo = Modelo("BMW X6", 2020)
        insereModelo(db, modelo)

        val carro = Carro(2020, combustivel.id, modelo.id, utilizador.id)
        insereCarro(db, carro)

        val tipoDespesa = TipoDespesa("Combustivel")
        insereTipoDespesa(db, tipoDespesa)

        val despesa = Despesa(tipoDespesa.id, 20210623, 60.52f, carro.id)

        insereDespesa(db, despesa)

        db.close()
    }

    @Test
    fun consegueAlterarDespesa(){
        val db = getWritableDatabase()

        val utilizador = Utilizador("Ricardo Sousa", 20020625)
        insereUtilizador(db, utilizador)

        val combustivel = Combustivel("Gasoleo")
        insereCombustivel(db, combustivel)

        val modelo = Modelo("BMW X6", 2020)
        insereModelo(db, modelo)

        val carro = Carro(2020, combustivel.id, modelo.id, utilizador.id)
        insereCarro(db, carro)

        val tipoDespesa = TipoDespesa("Combustivel")
        insereTipoDespesa(db, tipoDespesa)

        val despesa = Despesa(tipoDespesa.id, 20210623, 50.05f, carro.id)

        insereDespesa(db, despesa)

        despesa.data_despesa = 20200520
        despesa.valor_despesa = 20.05f

        val registoAlterado = TabelaBDDespesas(db).update(
            despesa.toContentValues(),
            "${BaseColumns._ID}=?",
            arrayOf("${despesa.id}")
        )

        assertEquals(1, registoAlterado)

        db.close()
    }
    
    @Test
    fun consegueLerUtilizadores(){
        val db = getWritableDatabase()

        val utilizador = Utilizador("Ricardo Sousa", 20020625)
        insereUtilizador(db, utilizador)

        val cursor = TabelaBDUtilizadores(db).query(
            TabelaBDUtilizadores.TODAS_COLUNAS,
            "${BaseColumns._ID}=?",
            arrayOf("${utilizador.id}"),
            null,
            null,
            null
        )

        assertEquals(1, cursor.count)
        assertTrue(cursor.moveToNext())

        val utilizadoresBD = Utilizador.fromCursor(cursor)

        assertEquals(utilizador, utilizadoresBD)

        db.close()
    }

    @Test
    fun consegueLerTipoCombustivel(){
        val db = getWritableDatabase()

        val combustivel = Combustivel("Gasoleo")

        insereCombustivel(db, combustivel)

        val cursor = TabelaBDTipoCombustivel(db).query(
            TabelaBDTipoCombustivel.TODAS_COLUNAS,
            "${BaseColumns._ID}=?",
            arrayOf("${combustivel.id}"),
            null,
            null,
            null
        )

        assertEquals(1, cursor.count)
        assertTrue(cursor.moveToNext())

        val tipoCombustivelBD = Combustivel.fromCursor(cursor)

        assertEquals(combustivel, tipoCombustivelBD)

        db.close()
    }

    @Test
    fun consegueLerModelos(){
        val db = getWritableDatabase()

        val modelo = Modelo("BMW X6", 20200620)

        insereModelo(db, modelo)

        val cursor = TabelaBDModelos(db).query(
            TabelaBDModelos.TODAS_COLUNAS,
            "${BaseColumns._ID}=?",
            arrayOf("${modelo.id}"),
            null,
            null,
            null
        )

        assertEquals(1, cursor.count)
        assertTrue(cursor.moveToNext())

        val tipoModeloBD = Modelo.fromCursor(cursor)

        assertEquals(modelo, tipoModeloBD)

        db.close()
    }

    @Test
    fun consegueLerCarros(){
        val db = getWritableDatabase()

        val modelo = Modelo("BMW X6", 25062020)
        val combustivel = Combustivel("Gasoleo")
        val utilizador = Utilizador("Ricardo Sousa", 20020625)

        insereModelo(db, modelo)
        insereCombustivel(db, combustivel)
        insereUtilizador(db, utilizador)

        val carro = Carro(25062020, combustivel.id, modelo.id, utilizador.id)

        insereCarro(db, carro)

        val cursor = TabelaBDCarros(db).query(
            TabelaBDCarros.TODAS_COLUNAS,
            "${BaseColumns._ID}=?",
            arrayOf("${carro.id}"),
            null,
            null,
            null
        )

        assertEquals(1, cursor.count)
        assertTrue(cursor.moveToNext())

        val carroBD = Carro.fromCursor(cursor)

        assertEquals(carro, carroBD)

        db.close()
    }

    @Test
    fun consegueLerTipoDespesa(){
        val db = getWritableDatabase()

        val tipoDespesa = TipoDespesa("Combustivel")
        insereTipoDespesa(db, tipoDespesa)

        val cursor = TabelaBDTipoDespesa(db).query(
            TabelaBDTipoDespesa.TODAS_COLUNAS,
            "${BaseColumns._ID}=?",
            arrayOf("${tipoDespesa.id}"),
            null,
            null,
            null
        )

        assertEquals(1, cursor.count)
        assertTrue(cursor.moveToNext())

        val tipoDespesaBD = TipoDespesa.fromCursor(cursor)

        assertEquals(tipoDespesa, tipoDespesaBD)

        db.close()
    }

    @Test
    fun consegueLerDespesa(){
        val db = getWritableDatabase()

        val utilizador = Utilizador("Ricardo Sousa", 20020625)
        val combustivel = Combustivel("Gasoleo")
        val modelo = Modelo("BMW X6", 20200620)
        insereUtilizador(db, utilizador)
        insereCombustivel(db, combustivel)
        insereModelo(db, modelo)

        val carro = Carro(2020, combustivel.id, modelo.id, utilizador.id)
        insereCarro(db, carro)

        val tipoDespesa = TipoDespesa("Combustivel")
        insereTipoDespesa(db, tipoDespesa)

        val despesa = Despesa(tipoDespesa.id, 20210623, 60.52f, carro.id)
        insereDespesa(db, despesa)

        val cursor = TabelaBDDespesas(db).query(
            TabelaBDDespesas.TODAS_COLUNAS,
            "${BaseColumns._ID}=?",
            arrayOf("${despesa.id}"),
            null,
            null,
            null
        )

        assertEquals(1, cursor.count)
        assertTrue(cursor.moveToNext())

        val despesaBD = Despesa.fromCursor(cursor)

        assertEquals(despesa, despesaBD)

        db.close()
    }
}