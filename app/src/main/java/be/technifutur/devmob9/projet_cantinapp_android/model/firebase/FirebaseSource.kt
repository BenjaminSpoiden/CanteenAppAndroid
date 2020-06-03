package be.technifutur.devmob9.projet_cantinapp_android.model.firebase

import android.nfc.Tag
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import be.technifutur.devmob9.projet_cantinapp_android.utils.security.MD5Hashing
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import com.google.firebase.firestore.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.firestoreSettings
import com.google.firebase.ktx.Firebase
import io.reactivex.Completable
import java.text.DateFormat
import java.util.*
import java.time.*
import java.time.format.DateTimeFormatter
import kotlin.collections.HashMap

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


    @RequiresApi(Build.VERSION_CODES.O)
    fun addDateToDB(){
        val city = hashMapOf(
            "name" to getDateTime(),
            "state" to "CA",
            "country" to "USA"
        )

        db.collection(COLLECTION_ID).document(getDateTime())
            .set(city)
            .addOnSuccessListener {
                Log.d(TAG, "Successful -> $it")
            }
            .addOnFailureListener{
                Log.d(TAG, "Not successful", it)
            }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getDateFromMeal() {
//        val current = LocalDateTime.now()
//        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ROOT)
//        val formatted = formatter.format(current)
//
//        db.collection(COLLECTION_ID).document(getDateTime())
//            .get()
//            .addOnCompleteListener {
//                if(it.isSuccessful){
//                    Log.d(TAG, "DocumentSnapshot Data: ${it.result}")
//                }else {
//                    Log.d(TAG, "No such document", it.exception)
//                }
//            }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getDateTime(): String {
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ROOT)
        return formatter.format(current)
    }
}