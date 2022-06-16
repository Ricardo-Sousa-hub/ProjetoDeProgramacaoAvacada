package pt.ipg.projetofinal.ui.utilizadores

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UtilizadoresViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is utilizadores Fragment"
    }
    val text: LiveData<String> = _text
}