package be.technifutur.devmob9.projet_cantinapp_android.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CartItemViewModel: ViewModel() {

    private val mutableNumberOfItemSelected = MutableLiveData<Int>()
    val numberOfItemSelected: LiveData<Int>
        get() = mutableNumberOfItemSelected


    fun onNumberOfItemSelected(numberOfItemSelected: Int) {
        mutableNumberOfItemSelected.postValue(numberOfItemSelected)
    }

}