package pt.ipg.projetofinal.ui.estatistica

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import pt.ipg.projetofinal.databinding.FragmentEstatisticaBinding

class EstatisticaFragment : Fragment() {

    private var _binding: FragmentEstatisticaBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val tipoDespesaViewModel =
            ViewModelProvider(this).get(EstatisticaViewModel::class.java)

        _binding = FragmentEstatisticaBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textEstatistica
        tipoDespesaViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}