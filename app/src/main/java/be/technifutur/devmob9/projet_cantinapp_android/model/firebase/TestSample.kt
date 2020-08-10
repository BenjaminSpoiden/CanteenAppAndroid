package be.technifutur.devmob9.projet_cantinapp_android.model.firebase

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import be.technifutur.devmob9.projet_cantinapp_android.model.data.Menu
import be.technifutur.devmob9.projet_cantinapp_android.model.data.SampleTestModel
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.COLLECTION_ID_DAYS
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.FIREBASE_TAG
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.ID_DAYS_MEALS
import com.google.firebase.firestore.FirebaseFirestore
import java.lang.NullPointerException

@Deprecated("")
class TestSample private constructor(){

    private object Holder {
        var instance = TestSample()
    }

    companion object {
        val INSTANCE by lazy {
            Holder.instance
        }
    }

    private val db = FirebaseFirestore.getInstance()

    fun test(): LiveData<MutableList<SampleTestModel>> {
        val mutableTestData = MutableLiveData<MutableList<SampleTestModel>>()
        db.collection(ID_DAYS_MEALS)
            .addSnapshotListener { querySnapshot, exception ->
                exception?.let {
                    Log.d(FIREBASE_TAG, "${it.cause}")
                    return@addSnapshotListener
                }
                querySnapshot?.let { query ->
                    val dataList = mutableListOf<SampleTestModel>()
                    query.documents.forEach {
                        val data = it.toObject(SampleTestModel::class.java)
                        if (data != null) {
                            dataList.add(data)
                        }
//                        Log.d(FIREBASE_TAG, "for ${it.id} doc snap2 keys : ${it.data?.keys} \n and values: ${it.data?.values}")
                        Log.d("FirebaseTest2", "toObject: $data", exception)

                        mutableTestData.value = dataList
                    }
                }
            }
        return mutableTestData
    }

    fun test2(){
        db.collection(ID_DAYS_MEALS).document("2020-07-09").get()
            .addOnSuccessListener {
                Log.d("DataWhere", "${it.data}")
            }
            .addOnFailureListener {
                Log.d("DataWhere", "${it.cause}")
            }
    }
}