package pt.ipg.projetofinal

import android.database.Cursor
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SimpleCursorAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import pt.ipg.projetofinal.databinding.FragmentInserirCarroBinding
import java.util.*


/**
 * A simple [Fragment] subclass.
 * Use the [InserirCarroFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InserirCarroFragment : Fragment(), LoaderManager.LoaderCallbacks<Cursor> {

    private var _binding: FragmentInserirCarroBinding? = null

    private var carro: Carro? = null

    private lateinit var utilizador: Utilizador

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInserirCarroBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        LoaderManager.getInstance(this).initLoader(ID_LOADER_MODELO, null, this)
        LoaderManager.getInstance(this).initLoader(ID_LOADER_TIPO_COMBUSTIVEL, null, this)

        utilizador = InserirCarroFragmentArgs.fromBundle(arguments!!).utilizador

        binding.textViewCarroPertenceA.text = utilizador.nome

        val activity = requireActivity() as MainActivity
        activity.findViewById<Button>(R.id.buttonInserirCarro1).setOnClickListener(){
            guardarCarro()
        }
    }

    private fun guardarCarro() {

        var data : Long = 0

        binding.calendarViewDataCarro.setOnDateChangeListener(){
                calendarView, ano, mes, dia ->
            val date = Calendar.getInstance()
            date.set(ano, mes, dia)
            data = date.timeInMillis
        }

        val idModelo = binding.spinnerModelo.selectedItemId

        val idTipoCombustivel = binding.spinnerTipoCombustivel.selectedItemId

        val carro = Carro(data, Combustivel(id = idTipoCombustivel), Modelo(id = idModelo), utilizador)

        val endereco = requireActivity().contentResolver.insert(ContentProviderApp.ENDERECO_CARROS, carro.toContentValues())

        if(endereco != null){
            Toast.makeText(requireContext(), getString(R.string.sucesso_inserir_utilizador), Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_navigation_inserir_Carro_to_navigation_carros)
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
        if(id == ID_LOADER_MODELO){
            return CursorLoader(
                requireContext(),
                ContentProviderApp.ENDERECO_MODELO,
                TabelaBDModelos.TODAS_COLUNAS,
                null,
                null,
                TabelaBDModelos.NOME_MODELO
            )
        }else{
            return CursorLoader(
                requireContext(),
                ContentProviderApp.ENDERECO_TIPO_COMBUSTIVEL,
                TabelaBDTipoCombustivel.TODAS_COLUNAS,
                null,
                null,
                TabelaBDTipoCombustivel.NOME_COMBUSTIVEL
            )
        }
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
        if(loader.id == ID_LOADER_MODELO){
            val adapterModelo = SimpleCursorAdapter(
                requireContext(),
                android.R.layout.simple_list_item_1,
                data,
                arrayOf(TabelaBDModelos.NOME_MODELO),
                intArrayOf(android.R.id.text1),
                0
            )

            binding.spinnerModelo.adapter = adapterModelo
        }
        else{
            val adapterTipoCombustivel = SimpleCursorAdapter(
                requireContext(),
                android.R.layout.simple_list_item_2,
                data,
                arrayOf(TabelaBDTipoCombustivel.NOME_COMBUSTIVEL),
                intArrayOf(android.R.id.text2),
                0
            )

            binding.spinnerTipoCombustivel.adapter = adapterTipoCombustivel
        }


        atualizaCategoriaSelecionada()
    }

    private fun atualizaCategoriaSelecionada() {
        if (carro == null) return
        val idModelo = carro!!.modelo.id

        val ultimoModelo = binding.spinnerModelo.count - 1

        for (i in 0..ultimoModelo) {
            if (binding.spinnerModelo.getItemIdAtPosition(i) == idModelo) {
                binding.spinnerModelo.setSelection(i)
                return
            }
        }
        val idTipoCombustivel = carro!!.combustivel.id

        val ultimoCombustivel = binding.spinnerTipoCombustivel.count - 1

        for (i in 0..ultimoCombustivel) {
            if (binding.spinnerTipoCombustivel.getItemIdAtPosition(i) == idTipoCombustivel) {
                binding.spinnerTipoCombustivel.setSelection(i)
                return
            }
        }
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
        if (_binding == null) return
        binding.spinnerModelo.adapter = null
    }



    companion object {
        const val ID_LOADER_MODELO = 0
        const val ID_LOADER_TIPO_COMBUSTIVEL = 1
    }
}