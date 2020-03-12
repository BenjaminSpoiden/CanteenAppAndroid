package be.technifutur.devmob9.projet_cantinapp_android.viewmodel

import android.view.View
import android.widget.Button
import androidx.lifecycle.ViewModel
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.interfaces.AuthListener

class LoginViewModel: ViewModel(){
    var username: String? = null
    var password: String? = null
    var isCredentialsValid: Boolean? = false

    var authListener: AuthListener? = null
    fun onLoginButtonClicked(view: View){
        authListener?.onStarted()
        val loginButton: Button = view.findViewById(R.id.login_button)

        if(username.isNullOrEmpty() || password.isNullOrEmpty()){
            authListener?.onError("Invalid username or password")
            isCredentialsValid = false
            return
        }

        if(!username.isNullOrEmpty() && password!!.length > 6){
            isCredentialsValid = true
        }
        authListener?.onSuccess()
    }
}