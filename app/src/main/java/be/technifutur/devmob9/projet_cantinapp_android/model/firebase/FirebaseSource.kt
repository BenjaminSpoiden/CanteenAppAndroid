package be.technifutur.devmob9.projet_cantinapp_android.model.firebase

import be.technifutur.devmob9.projet_cantinapp_android.utils.security.MD5Hashing
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import io.reactivex.Completable

class FirebaseSource{

    companion object{
        const val USERS: String = "users"
    }

    private val firebaseAuth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }
    private val database: FirebaseDatabase by lazy {
        FirebaseDatabase.getInstance()
    }
    private val databaseReference: DatabaseReference by lazy {
        database.reference
    }


    //Completable allow to maintain something that will complete. We can get an indication when it is completed or not.

    fun login(email: String, password: String) = Completable.create { emitter ->
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if(!emitter.isDisposed){
                if(it.isSuccessful){
                    emitter.onComplete()
                }else{
                    emitter.onError(it.exception!!)
                }
            }
        }
    }

    fun register(email: String, password: String) = Completable.create { emitter ->
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { result ->
            if(!emitter.isDisposed){
                if(result.isSuccessful){
                    val userID: String? = currentUser()?.uid
                    userID?.let {
                        val hashMap: HashMap<String, String> = HashMap()
                        hashMap["email"] = email
                        hashMap["password"] = password.MD5Hashing()
                        databaseReference.child(USERS).child(it).setValue(hashMap)
                    }
                    emitter.onComplete()
                }else{
                    emitter.onError(result.exception!!)
                }
            }
        }
    }
    fun logout() = firebaseAuth.signOut()
    fun currentUser() = firebaseAuth.currentUser

}