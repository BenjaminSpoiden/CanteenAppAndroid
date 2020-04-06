package be.technifutur.devmob9.projet_cantinapp_android.interfaces

interface AuthListener {
    fun onStarted()
    fun onSuccess(username: String)
    fun onError(message: String)
}