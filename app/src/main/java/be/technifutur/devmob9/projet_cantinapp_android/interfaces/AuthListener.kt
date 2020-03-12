package be.technifutur.devmob9.projet_cantinapp_android.interfaces

interface AuthListener {
    fun onStarted()
    fun onSuccess()
    fun onError(message: String)
}