package pt.ipg.projetofinal.ui.despesas

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DespesasViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is despesas Fragment"
    }
    val text: LiveData<String> = _text
}