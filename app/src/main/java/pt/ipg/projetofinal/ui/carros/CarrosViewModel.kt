package pt.ipg.projetofinal.ui.carros

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CarrosViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is carros Fragment"
    }
    val text: LiveData<String> = _text
}