package pt.ipg.projetofinal

import android.database.sqlite.SQLiteDatabase
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

        insereUtilizador(db, Utilizador("Ricardo Sousa", "2002-06-25"))

        db.close()
    }

    @Test
    fun consegueInserirCombustivel(){
        val db = getWritableDatabase()

        insereCombustivel(db, Combustivel("Gasoleo"))

        db.close()
    }

    @Test
    fun consegueInserirModelo(){
        val db = getWritableDatabase()

        insereModelo(db, Modelo("BMW X6", "2020"))

        db.close()
    }

    @Test
    fun consegueInserirCarro(){
        val db = getWritableDatabase()

        val utilizador = Utilizador("Ricardo Sousa", "2002-06-25")
        insereUtilizador(db, utilizador)

        val combustivel = Combustivel("Gasoleo")
        insereCombustivel(db, combustivel)

        val modelo = Modelo("BMW X6", "2020")
        insereModelo(db, modelo)

        val carro = Carro("2020", combustivel.id, modelo.id, utilizador.id)
        insereCarro(db, carro)

        db.close()

    }

}