package be.technifutur.devmob9.projet_cantinapp_android.viewmodel


import androidx.lifecycle.*
import be.technifutur.devmob9.projet_cantinapp_android.model.firebase.ContactPageManager

class ContactPageViewModel(private val contactPageManager: ContactPageManager): ViewModel() {

    /**
     * When we use two way databinding, we're forced to use MutableLiveData otherwise the layout can't access the values.
     */

    //Example of more secure code to write when not using two way databinding:
    private val mutableValue = MutableLiveData<Any>()
    val value: LiveData<Any>
        get() = mutableValue

    var serviceToContact = MutableLiveData<String>()
    var objectContact = MutableLiveData<String>()
    var orderNumber = MutableLiveData<String>()
    var message = MutableLiveData<String>()

    var isCheckboxChecked = MutableLiveData<Boolean>()

    val chosenFile: LiveData<String>
        get() = contactPageManager.currentDocument

    fun onClickChoosingFile() = contactPageManager.onChangedDocument()



}