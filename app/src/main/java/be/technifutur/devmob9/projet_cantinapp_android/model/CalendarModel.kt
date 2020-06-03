package be.technifutur.devmob9.projet_cantinapp_android.model

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import be.technifutur.devmob9.projet_cantinapp_android.model.firebase.FirebaseSource
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.getField
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


@RequiresApi(Build.VERSION_CODES.O)
data class CalendarModel(val day: String, val dayNumber: String) {

    val db = FirebaseFirestore.getInstance()
    val meals = db.collection(FirebaseSource.COLLECTION_ID)

    @RequiresApi(Build.VERSION_CODES.O)
    val mealsDoc = meals.document(getDateTime())

    init {
        mealsDoc.addSnapshotListener { documentSnapshot, firebaseFirestoreException ->
            documentSnapshot?.get(FieldPath.of("meals/${getDateTime()}"))
            val data = documentSnapshot?.data

            data?.let {

            }
            Log.d(FirebaseSource.TAG, "Document Data: ${documentSnapshot?.data}", firebaseFirestoreException)
        }
    }



    @RequiresApi(Build.VERSION_CODES.O)
    private fun getDateTime(): String {
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ROOT)
        return formatter.format(current)
    }
}