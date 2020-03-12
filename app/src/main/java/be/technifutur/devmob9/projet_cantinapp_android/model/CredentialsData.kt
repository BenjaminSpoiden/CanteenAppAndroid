package be.technifutur.devmob9.projet_cantinapp_android.model

import android.text.TextUtils
import androidx.databinding.BaseObservable

data class CredentialsData(var username: String, var password: String): BaseObservable() {
    val isDataValid: Boolean
    get() = (!TextUtils.isEmpty(username)
            && password.length > 8)

}