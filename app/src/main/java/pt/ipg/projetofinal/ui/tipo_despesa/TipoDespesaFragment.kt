package pt.ipg.projetofinal.ui.tipo_despesa

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import pt.ipg.projetofinal.databinding.FragmentTipoDespesaBinding

class TipoDespesaFragment : Fragment() {

    private var _binding: FragmentTipoDespesaBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val tipoDespesaViewModel =
            ViewModelProvider(this).get(TipoDespesaViewModel::class.java)

        _binding = FragmentTipoDespesaBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textTipoDespesa
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