package pt.ipg.projetofinal.ui.utilizadores

import android.content.Context
import android.database.Cursor
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import androidx.recyclerview.widget.LinearLayoutManager
import pt.ipg.projetofinal.databinding.FragmentUtilizadoresBinding
import androidx.navigation.fragment.findNavController
import pt.ipg.projetofinal.*

class UtilizadoresFragment : Fragment(), LoaderManager.LoaderCallbacks<Cursor> {

    var utilizadorSeleccionado : Utilizador? = null
        get() = field
        set(value) {
            field = value
        }

    private var _binding: FragmentUtilizadoresBinding? = null
    private var adapterUtilizadores : AdapterUtilizadores? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUtilizadoresBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        LoaderManager.getInstance(this).initLoader(ID_LOADER_UTILIZADORES, null, this)

        adapterUtilizadores = AdapterUtilizadores(this)
        binding.recyclerViewUtilizadores.adapter = adapterUtilizadores
        binding.recyclerViewUtilizadores.layoutManager = LinearLayoutManager(requireContext())

        //inserirModelos()
        //inserirTiposDeCombustivel()

        binding.buttonInserirUtilizador.setOnClickListener() {
            findNavController().navigate(R.id.action_navigation_utilizadores_to_inserirUtilizadorFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /**
     * Instantiate and return a new Loader for the given ID.
     *
     *
     * This will always be called from the process's eliminarUtilizador thread.
     *
     * @param id The ID whose loader is to be created.
     * @param args Any arguments supplied by the caller.
     * @return Return a new Loader instance that is ready to start loading.
     */
    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> =
        CursorLoader(
            requireContext(),
            ContentProviderApp.ENDERECO_UTILIZADOR,
            TabelaBDUtilizadores.TODAS_COLUNAS,
            null,
            null,
            TabelaBDUtilizadores.NOME
        )

    /**
     * Called when a previously created loader has finished its load.  Note
     * that normally an application is *not* allowed to commit fragment
     * transactions while in this call, since it can happen after an
     * activity's state is saved.  See [ FragmentManager.openTransaction()][androidx.fragment.app.FragmentManager.beginTransaction] for further discussion on this.
     *
     *
     * This function is guaranteed to be called prior to the release of
     * the last data that was supplied for this Loader.  At this point
     * you should remove all use of the old data (since it will be released
     * soon), but should not do your own release of the data since its Loader
     * owns it and will take care of that.  The Loader will take care of
     * management of its data so you don't have to.  In particular:
     *
     *
     *  *
     *
     *The Loader will monitor for changes to the data, and report
     * them to you through new calls here.  You should not monitor the
     * data yourself.  For example, if the data is a [android.database.Cursor]
     * and you place it in a [android.widget.CursorAdapter], use
     * the [android.widget.CursorAdapter.CursorAdapter] constructor *without* passing
     * in either [android.widget.CursorAdapter.FLAG_AUTO_REQUERY]
     * or [android.widget.CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER]
     * (that is, use 0 for the flags argument).  This prevents the CursorAdapter
     * from doing its own observing of the Cursor, which is not needed since
     * when a change happens you will get a new Cursor throw another call
     * here.
     *  *  The Loader will release the data once it knows the application
     * is no longer using it.  For example, if the data is
     * a [android.database.Cursor] from a [android.content.CursorLoader],
     * you should not call close() on it yourself.  If the Cursor is being placed in a
     * [android.widget.CursorAdapter], you should use the
     * [android.widget.CursorAdapter.swapCursor]
     * method so that the old Cursor is not closed.
     *
     *
     *
     * This will always be called from the process's eliminarUtilizador thread.
     *
     * @param loader The Loader that has finished.
     * @param data The data generated by the Loader.
     */
    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        adapterUtilizadores!!.cursor = data
    }

    /**
     * Called when a previously created loader is being reset, and thus
     * making its data unavailable.  The application should at this point
     * remove any references it has to the Loader's data.
     *
     *
     * This will always be called from the process's eliminarUtilizador thread.
     *
     * @param loader The Loader that is being reset.
     */
    override fun onLoaderReset(loader: Loader<Cursor>) {
        adapterUtilizadores!!.cursor = null
    }

    companion object{
        const val ID_LOADER_UTILIZADORES = 0
    }

    private fun inserirTiposDeCombustivel(){

        val eletricidade = Combustivel("Eletricidade")
        val gas = Combustivel("Gas")
        val gasoleo = Combustivel("Gasoleo")
        val gasolina = Combustivel("Gasolina")

        requireContext().contentResolver.insert(ContentProviderApp.ENDERECO_TIPO_COMBUSTIVEL, eletricidade.toContentValues())
        requireContext().contentResolver.insert(ContentProviderApp.ENDERECO_TIPO_COMBUSTIVEL, gas.toContentValues())
        requireContext().contentResolver.insert(ContentProviderApp.ENDERECO_TIPO_COMBUSTIVEL, gasoleo.toContentValues())
        requireContext().contentResolver.insert(ContentProviderApp.ENDERECO_TIPO_COMBUSTIVEL, gasolina.toContentValues())
    }

    private fun inserirModelos(){

        val bmw = Modelo("BMW X6", 5062020)
        val audi = Modelo("AUDI A6", 2082015)
        val mercedes = Modelo("MERCEDES BENZ", 25062012)

        requireContext().contentResolver.insert(ContentProviderApp.ENDERECO_MODELO, bmw.toContentValues())
        requireContext().contentResolver.insert(ContentProviderApp.ENDERECO_MODELO, audi.toContentValues())
        requireContext().contentResolver.insert(ContentProviderApp.ENDERECO_MODELO, mercedes.toContentValues())
    }
}