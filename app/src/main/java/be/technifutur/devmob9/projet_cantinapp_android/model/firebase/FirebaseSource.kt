package be.technifutur.devmob9.projet_cantinapp_android.model.firebase

import android.util.Log
import be.technifutur.devmob9.projet_cantinapp_android.utils.security.MD5Hashing
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.MetadataChanges
import com.google.firebase.firestore.Source
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.firestoreSettings
import com.google.firebase.ktx.Firebase
import io.reactivex.Completable

class FirebaseSource{

    companion object{
        const val USERS: String = "users"
        const val COLLECTION_ID: String = "meals"
        const val TAG = "FirebaseTest"
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
    private val settings = FirebaseFirestoreSettings.Builder().setCacheSizeBytes(FirebaseFirestoreSettings.CACHE_SIZE_UNLIMITED).build()
    private val offline = firestoreSettings {
        isPersistenceEnabled = true
    }
    private val db: FirebaseFirestore by lazy {
        Firebase.firestore
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

    fun getDateFromMeal() {
        db.firestoreSettings = settings

        db.collection(COLLECTION_ID).document("TEST")
            .get()
            .addOnCompleteListener {
                if(it.isSuccessful){
                    val document = it.result
                    Log.d(TAG, "DocumentSnapshot data: ${document?.data}")
                }else {
                    Log.d(TAG, "No such document", it.exception)
                }
            }
    }
}