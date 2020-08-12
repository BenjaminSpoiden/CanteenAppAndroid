package be.technifutur.devmob9.projet_cantinapp_android.model.firebase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import be.technifutur.devmob9.projet_cantinapp_android.model.data.DishesType
import be.technifutur.devmob9.projet_cantinapp_android.model.data.SampleTestModel
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.ID_DAYS_MEALS
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.ID_DESSERTS
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.ID_MAIN_COURSES
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.ID_STARTERS
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject

class MenusManager {

    private val db = FirebaseFirestore.getInstance()

    private val mutableMenusLiveData = MutableLiveData<DishesType>()
    val menusLiveData: LiveData<DishesType>
        get() = mutableMenusLiveData


    fun onRetrievedMenusFromDate(date: String){
        db.collection(ID_DAYS_MEALS)
            .document(date)
            .addSnapshotListener { documentSnapshot, firebaseFirestoreException ->
                firebaseFirestoreException?.let {
                    return@addSnapshotListener
                }
                documentSnapshot?.let {
                    val data = it.toObject<SampleTestModel>()
                    data?.menu?.starters?.forEach { onRetrieveStarters(it) }
                    data?.menu?.main_courses?.forEach { onRetrieveMainCourses(it) }
                    data?.menu?.desserts?.forEach { onRetrievedDesserts(it) }
                }
            }
    }

    private fun onRetrieveStarters(starterName: String){
        db.collection(ID_STARTERS)
            .document(starterName)
            .addSnapshotListener { documentSnapshot, e ->
                e?.let {
                    return@let
                }
                documentSnapshot?.let {
                    val starterData = it.toObject(DishesType.Starters::class.java)
                    mutableMenusLiveData.value = starterData
                }
            }
    }

    private fun onRetrieveMainCourses(mainsName: String) {
        db.collection(ID_MAIN_COURSES)
            .document(mainsName)
            .addSnapshotListener { documentSnapshot, e ->
                e?.let {
                    return@let
                }
                documentSnapshot?.let {
                    val mainsData = it.toObject<DishesType.MainCourses>()
                    mutableMenusLiveData.value = mainsData
                }
            }
    }

    private fun onRetrievedDesserts(dessertsName: String){
        db.collection(ID_DESSERTS)
            .document(dessertsName)
            .addSnapshotListener { documentSnapshot, e ->
                e?.let {
                    return@let
                }
                documentSnapshot?.let {
                    val dessertsData = it.toObject<DishesType.Desserts>()
                    mutableMenusLiveData.value = dessertsData
                }
            }
    }
}