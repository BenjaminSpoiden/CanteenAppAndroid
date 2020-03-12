package be.technifutur.devmob9.projet_cantinapp_android.viewmodel

import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.ViewModel
import be.technifutur.devmob9.projet_cantinapp_android.model.CredentialsData

class LoginViewModel(): ViewModel(){
    private val user: CredentialsData = CredentialsData("", "")

    val textWatcher: TextWatcher
        get() = object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                user.username = s.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        }

    val passwordTextWatcher: TextWatcher
        get() = object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                user.password = s.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        }

}