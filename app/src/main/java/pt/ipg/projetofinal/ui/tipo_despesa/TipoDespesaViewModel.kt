package pt.ipg.projetofinal.ui.tipo_despesa

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TipoDespesaViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is tipo de despesa Fragment"
    }
    val text: LiveData<String> = _text
}