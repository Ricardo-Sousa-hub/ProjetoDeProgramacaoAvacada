package pt.ipg.projetofinal

import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class BaseDadosUnitTest {

    fun appContext() =InstrumentationRegistry.getInstrumentation().targetContext

    @Before
    fun apagaBaseDados(){
        appContext().deleteDatabase(BDAppOpenHelper.NOME)
    }

    @Test
    fun consegueAbrirBaseDados(){
        val openHelper = BDAppOpenHelper(appContext())
        val db = openHelper.readableDatabase

        assertTrue(db.isOpen)
        db.close()
    }

}