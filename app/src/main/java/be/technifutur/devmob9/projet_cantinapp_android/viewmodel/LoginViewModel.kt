package be.technifutur.devmob9.projet_cantinapp_android.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import be.technifutur.devmob9.projet_cantinapp_android.interfaces.AuthListener

class LoginViewModel: ViewModel(){
    var username: String? = null
    var password: String? = null
    var isCredentialsValid: Boolean? = false
    var authListener: AuthListener? = null

    fun onLoginButtonClicked(view: View){

        if(username.isNullOrEmpty() || password.isNullOrEmpty()){
            authListener?.onError("Invalid username or password")

            isCredentialsValid = false
            return
        }

        if(!username.isNullOrEmpty() && password!!.length >= 8){
            isCredentialsValid = true
            authListener?.onSuccess()
        }else{
            authListener?.onError("Invalid")
        }
    }



}