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
import pt.ipg.projetofinal.databinding.FragmentEliminarCarroBinding
import pt.ipg.projetofinal.databinding.FragmentEliminarUtilizadorBinding

class EliminarCarro : Fragment() {

    private var _binding: FragmentEliminarCarroBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var carro: Carro

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEliminarCarroBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = requireActivity() as MainActivity
        activity.findViewById<Button>(R.id.buttonEliminarUtilizador).setOnClickListener(){
            eliminarUtilizador()
        }

        carro = EliminarCarroArgs.fromBundle(arguments!!).carro

        binding.textViewModeloCarro.text = carro.modelo.nome
        binding.textViewCombustivelCarro.text = carro.combustivel.nome

    }

    private fun eliminarUtilizador() {
        val enderecoCarro = Uri.withAppendedPath(ContentProviderApp.ENDERECO_CARROS, "${carro.id}")
        val registosEliminados = requireActivity().contentResolver.delete(enderecoCarro, null, null)

        if (registosEliminados != 1) {
            Snackbar.make(
                binding.textViewModeloCarro,
                R.string.insucesso_eliminar,
                Snackbar.LENGTH_INDEFINITE
            ).show()
            return
        }

        Toast.makeText(requireContext(), R.string.sucesso_eliminar, Toast.LENGTH_LONG).show()
        voltaListaCarros()
    }

    private fun voltaListaCarros() {
        findNavController().navigate(R.id.action_navigation_eliminar_utilizador_to_navigation_utilizadores)
    }

}