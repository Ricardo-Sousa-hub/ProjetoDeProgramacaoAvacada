package pt.ipg.projetofinal

import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import pt.ipg.projetofinal.databinding.FragmentEliminarTipoDespesaBinding
import pt.ipg.projetofinal.databinding.FragmentEliminarUtilizadorBinding

class EliminarTipoDespesa : Fragment() {

    private var _binding: FragmentEliminarTipoDespesaBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var tipoDespesa: TipoDespesa

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEliminarTipoDespesaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = requireActivity() as MainActivity
        activity.findViewById<Button>(R.id.buttonEliminarTipoDespesa).setOnClickListener(){
            eliminarTipoDespesa()
        }

        tipoDespesa = EliminarTipoDespesaArgs.fromBundle(arguments!!).tipoDeDespesa

        binding.textViewNomeEliminarTipoDespesa.text = tipoDespesa.nome

    }

    private fun eliminarTipoDespesa() {
        val enderecoTipoDespesa = Uri.withAppendedPath(ContentProviderApp.ENDERECO_TIPO_DESPESA, "${tipoDespesa.id}")
        val registosEliminados = requireActivity().contentResolver.delete(enderecoTipoDespesa, null, null)

        if (registosEliminados != 1) {
            Snackbar.make(
                binding.textViewNomeEliminarTipoDespesa,
                R.string.insucesso_eliminar,
                Snackbar.LENGTH_INDEFINITE
            ).show()
            return
        }

        Toast.makeText(requireContext(), R.string.sucesso_eliminar, Toast.LENGTH_LONG).show()
        voltaListaTipoDespesa()
    }

    private fun voltaListaTipoDespesa() {
        findNavController().navigate(R.id.action_eliminarTipoDespesa_to_navigation_tipo_despesa)
    }

}