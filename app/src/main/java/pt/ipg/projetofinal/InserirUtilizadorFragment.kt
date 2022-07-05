package pt.ipg.projetofinal

import android.app.Activity
import android.database.Cursor
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.text.isDigitsOnly
import androidx.loader.app.LoaderManager
import androidx.loader.content.Loader
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import pt.ipg.projetofinal.databinding.FragmentInserirTipoDeDespesaBinding
import pt.ipg.projetofinal.databinding.FragmentInserirUtilizadorBinding
import java.util.*


class InserirUtilizadorFragment : Fragment(), LoaderManager.LoaderCallbacks<Cursor> {

    private var _binding: FragmentInserirUtilizadorBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentInserirUtilizadorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun guardarUtilizador(){
        val nome = binding.editTextTextInserirNome.text.toString()
        if(nome.isBlank()){
            binding.editTextTextInserirNome.error = getString(R.string.aviso_digite_nome)
            binding.editTextTextInserirNome.requestFocus()
            return
        }

        if(nome.isDigitsOnly()){
            binding.editTextTextInserirNome.error = getString(R.string.aviso_apenas_letras)
            binding.editTextTextInserirNome.requestFocus()
            return
        }

        var data : Long = 0

        binding.calendarViewDataUtilizador.setOnDateChangeListener(){
            calendarView, ano, mes, dia ->
            val date = Calendar.getInstance()
            date.set(ano, mes, dia)
            data = date.timeInMillis
        }

        val utilizador = Utilizador(nome, data)

        val endereco = requireActivity().contentResolver.insert(
            ContentProviderApp.ENDERECO_UTILIZADOR,
            utilizador.toContentValues()
        )

        if(endereco != null){
            Toast.makeText(requireContext(), getString(R.string.sucesso_inserir_utilizador), Toast.LENGTH_LONG).show()
            Limpar()
            findNavController().navigate(R.id.action_navigation_inserir_Utilizador_Fragment_to_navigation_utilizadores)
        }
        else{
            Snackbar.make(binding.editTextTextInserirNome, getString(R.string.falha_inserir_utilizador), Snackbar.LENGTH_INDEFINITE).show()
        }

    }

    fun Limpar(){
        binding.editTextTextInserirNome.text.clear()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = requireActivity() as MainActivity
        activity.findViewById<Button>(R.id.buttonInserirUtilizador1).setOnClickListener(){
            guardarUtilizador()
        }
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
    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {
        TODO()
    }

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
        /*binding.spinnerCategorias.adapter = SimpleCursorAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            data,
            arrayOf(TabelaBDCategorias.CAMPO_NOME),
            intArrayOf(android.R.id.text1),
            0
        )*/
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
        //binding.spinnerCategorias.adapter = null
    }
}
