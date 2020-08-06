package be.technifutur.devmob9.projet_cantinapp_android.model.firebase

import android.util.Log
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.EMAIL
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.PASSWORD
import be.technifutur.devmob9.projet_cantinapp_android.utils.security.MD5Hashing
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import io.reactivex.Completable
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter
import java.util.*
import kotlin.collections.HashMap

class AuthManager{

    private val firebaseAuth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }
    private val database: FirebaseDatabase by lazy {
        FirebaseDatabase.getInstance()
    }
    private val databaseReference: DatabaseReference by lazy {
        database.reference
    }
    private val db: FirebaseFirestore by lazy {
        Firebase.firestore
    }

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
                        hashMap[EMAIL] = email
                        hashMap[PASSWORD] = password.MD5Hashing()
                        databaseReference.child(Constants.USERS).child(it).setValue(hashMap)
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


    /**
     * Test of Adding data to firebase firestore
     */
    fun addDateToDB(){
        val city = hashMapOf(
            "name" to getDateTime(),
            "state" to "CA",
            "country" to "USA"
        )

        db.collection(Constants.COLLECTION_ID_DAYS).document(getDateTime())
            .set(city)
            .addOnSuccessListener {
                Log.d(Constants.FIREBASE_TAG, "Successful -> $it")
            }
            .addOnFailureListener{
                Log.d(Constants.FIREBASE_TAG, "Not successful", it)
            }
    }

    private fun getDateTime(): String {
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ROOT)
        return formatter.format(current)
    }
}