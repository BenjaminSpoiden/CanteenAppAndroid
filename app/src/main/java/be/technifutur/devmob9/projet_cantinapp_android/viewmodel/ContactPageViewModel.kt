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


    val mediator = MediatorLiveData<Boolean>()

    init {
        mediator.addSource(serviceToContact) { validation(serviceToContact) }
        mediator.addSource(objectContact) { validation(objectContact) }
        mediator.addSource(message) { validation(message) }
    }

    private fun validation(serviceToContact: MutableLiveData<String>) {
        mediator.value = !serviceToContact.value.isNullOrEmpty()
    }

    override fun onCleared() {
        super.onCleared()
        mediator.removeSource(serviceToContact)
        mediator.removeSource(objectContact)
        mediator.removeSource(message)
    }

    val chosenFile: LiveData<String>
        get() = contactPageRepository.currentDocument

    fun onClickChoosingFile() = contactPageRepository.onChangedDocument()

}