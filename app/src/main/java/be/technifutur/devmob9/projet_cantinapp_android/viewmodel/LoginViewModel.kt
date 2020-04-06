package be.technifutur.devmob9.projet_cantinapp_android.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import be.technifutur.devmob9.projet_cantinapp_android.interfaces.AuthListener

class LoginViewModel: ViewModel(){
    var username: String? = null
    var password: String? = null
    var authListener: AuthListener? = null

    //LiveData to upload the UI
    private val areCredentialsValid = MutableLiveData<Boolean>()
    val liveDataCredentials: LiveData<Boolean> = areCredentialsValid

//    fun checkingCredentials(view: View){
//        if(username.isNullOrEmpty() || password.isNullOrEmpty()){
//            areCredentialsValid.value = false
//        }else if(!username.isNullOrEmpty() && password!!.length >= 8){
//            areCredentialsValid.value = true
//
//            authListener?.onSuccess()
//        }else{
//            areCredentialsValid.value = false
//        }
//    }

}