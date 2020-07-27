package be.technifutur.devmob9.projet_cantinapp_android.model.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import be.technifutur.devmob9.projet_cantinapp_android.model.firebase.ContactPageManager
import java.util.*

class ContactPageRepository(private val contactPageManager: ContactPageManager) {

    private val listOfFakeDocuments = listOf(
        "Document 1",
        "Document 2",
        "Document 3",
        "Document 4"
    )

    private val mutableCurrentDocument = MutableLiveData<String>()
    val currentDocument: LiveData<String>
        get() = mutableCurrentDocument

    init {
        mutableCurrentDocument.value = listOfFakeDocuments.first()
    }

    private fun getRandomDocument(): String {
        return listOfFakeDocuments[Random().nextInt(listOfFakeDocuments.size)]
    }

    fun onChangedDocument() {
        mutableCurrentDocument.value = getRandomDocument()
    }

}