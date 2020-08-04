package be.technifutur.devmob9.projet_cantinapp_android.viewmodel


import androidx.lifecycle.*
import be.technifutur.devmob9.projet_cantinapp_android.model.repositories.ContactPageRepository

class ContactPageViewModel(private val contactPageRepository: ContactPageRepository): ViewModel() {

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

    private val _areFieldsFilledUp = MutableLiveData<Boolean>()

    init {
        onCheckingFields()
    }

    val chosenFile: LiveData<String>
        get() = contactPageRepository.currentDocument

    fun onClickChoosingFile() = contactPageRepository.onChangedDocument()

    fun onCheckingFields(): LiveData<Boolean> {
        _areFieldsFilledUp.value = !serviceToContact.value.isNullOrEmpty() && !objectContact.value.isNullOrEmpty() && !message.value.isNullOrEmpty()
        return _areFieldsFilledUp
    }
}