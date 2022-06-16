package pt.ipg.projetofinal.ui.carros

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import pt.ipg.projetofinal.databinding.FragmentCarrosBinding
class CarrosFragment : Fragment() {

    private var _binding: FragmentCarrosBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val carrosViewModel =
            ViewModelProvider(this).get(CarrosViewModel::class.java)

        _binding = FragmentCarrosBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textCarros
        carrosViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}