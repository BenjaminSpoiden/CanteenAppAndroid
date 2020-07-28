package be.technifutur.devmob9.projet_cantinapp_android.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import be.technifutur.devmob9.projet_cantinapp_android.model.repositories.ContactPageRepository
import be.technifutur.devmob9.projet_cantinapp_android.viewmodel.ContactPageViewModel

class ContactPageVMFactory(private val contactPageRepository: ContactPageRepository): ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ContactPageViewModel(contactPageRepository) as T
    }
}