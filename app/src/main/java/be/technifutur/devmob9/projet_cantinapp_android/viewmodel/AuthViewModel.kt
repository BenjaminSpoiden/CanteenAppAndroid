package be.technifutur.devmob9.projet_cantinapp_android.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import be.technifutur.devmob9.projet_cantinapp_android.interfaces.AuthListener
import be.technifutur.devmob9.projet_cantinapp_android.model.repositories.UserRepository
import com.google.firebase.auth.FirebaseUser
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class AuthViewModel(private val userRepository: UserRepository): ViewModel() {
    var email: String? = null
    var password: String? = null
    var confirmPassword: String? = null
    var authListener: AuthListener? = null


    private val isUserLoggedIn = MutableLiveData<Boolean>()
    val userLoggedIn: LiveData<Boolean> = isUserLoggedIn

    private val disposables = CompositeDisposable()

    val user: FirebaseUser? = userRepository.currentUser()

    fun login(){
        if(email.isNullOrEmpty() || password.isNullOrEmpty()){
            authListener?.onError("Empty Credentials")
            return
        }
        authListener?.onStarted()

        val disposable = userRepository
            .login(email!!, password!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                authListener?.onSuccess(email!!)
                isUserLoggedIn.value = true
            }, {
                authListener?.onError(it.message!!)
            })
        disposables.add(disposable)
    }

    fun register(){
        if(email.isNullOrEmpty() || password.isNullOrEmpty()){
            authListener?.onError("Wrong email or password")
            return
        }else if (password != confirmPassword){
            authListener?.onError("Password are not the same")
            return
        }
        authListener?.onStarted()

        val disposable = userRepository
            .register(email!!, password!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                authListener?.onSuccess(email!!)
            }, {
                authListener?.onError(it.message!!)
            })
        disposables.add(disposable)
    }

    fun logout(){
        userRepository.logout()
        isUserLoggedIn.value = false
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }
}