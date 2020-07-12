package be.technifutur.devmob9.projet_cantinapp_android.model.repositories

import be.technifutur.devmob9.projet_cantinapp_android.model.firebase.AuthManager

class UserRepository(private val authManager: AuthManager) {

    fun login(email: String, password: String) = authManager.login(email, password)
    fun register(email: String, password: String) = authManager.register(email, password)

    fun currentUser() = authManager.currentUser()
    fun logout() = authManager.logout()
}