package be.technifutur.devmob9.projet_cantinapp_android.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainFragmentViewModel: ViewModel() {

    private val mutableTitle = MutableLiveData<String>()
    val title: LiveData<String>
        get() = mutableTitle

    fun onUpdateActionBarTitle(title: String) {
        mutableTitle.value = title
    }
}