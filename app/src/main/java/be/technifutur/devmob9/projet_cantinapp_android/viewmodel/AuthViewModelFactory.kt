package be.technifutur.devmob9.projet_cantinapp_android.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import be.technifutur.devmob9.projet_cantinapp_android.model.repositories.UserRepository

class AuthViewModelFactory(private val userRepository: UserRepository): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AuthViewModel(userRepository) as T
    }
}