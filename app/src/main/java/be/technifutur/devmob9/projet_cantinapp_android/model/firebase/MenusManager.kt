package be.technifutur.devmob9.projet_cantinapp_android.model.firebase

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import be.technifutur.devmob9.projet_cantinapp_android.model.data.DishesType
import be.technifutur.devmob9.projet_cantinapp_android.model.data.Food
import be.technifutur.devmob9.projet_cantinapp_android.model.data.SampleTestModel
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.ID_DAYS_MEALS
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.ID_DESSERTS
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.ID_MAIN_COURSES
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.ID_STARTERS
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject

class MenusManager {

    private val db = FirebaseFirestore.getInstance()


    init {
        onRetrievedMenusFromDate("2020-07-09")
//        TODO("Need to find a way to write proper code and to have less of the same query for the three different classes." +
//                "Doing one class of type Food that retrieve all the data ? - POSSIBLE " +
//                "Implementing a listener of each type of food and directly connect that to the menu adapter ? " +
//                "Implementing a ViewModel type class where the three type are observed ? " +
//                "Do I still use the sealed Class ? Potential conflit with how I load the data inside the adapter.")
    }


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

    fun onRetrieveStarters(starterName: String){
        db.collection(ID_STARTERS)
            .document(starterName)
            .addSnapshotListener { documentSnapshot, e ->
                e?.let {
                    return@let
                }
                documentSnapshot?.let {
                    val starterData = it.toObject(DishesType.Starters::class.java)
                    Log.d("meals", "$starterData")
                }
            }
    }
    fun onRetrieveMainCourses(mainsName: String){
        db.collection(ID_MAIN_COURSES)
            .document(mainsName)
            .addSnapshotListener { documentSnapshot, e ->
                e?.let {
                    return@let
                }
                documentSnapshot?.let {
                    val mainsData = it.toObject<DishesType.Starters>()
                    Log.d("meals", "$mainsData")
                }
            }
    }

    fun onRetrievedDesserts(dessertsName: String){
        db.collection(ID_DESSERTS)
            .document(dessertsName)
            .addSnapshotListener { documentSnapshot, e ->
                e?.let {
                    return@let
                }
                documentSnapshot?.let {
                    val dessertsData = it.toObject<DishesType.Starters>()
                    Log.d("meals", "$dessertsData")
                }
            }
    }

    fun getStarterMenus(): LiveData<MutableList<DishesType.Starters>> {
        val mutableData = MutableLiveData<MutableList<DishesType.Starters>>()
        db.collection(ID_STARTERS).addSnapshotListener { querySnapshot, firebaseFirestoreException ->
            firebaseFirestoreException?.let {
                return@addSnapshotListener
            }
            querySnapshot?.let {
                val listOfData = mutableListOf<DishesType.Starters>()
                it.documents.forEach { document ->
                    val data = document.toObject(DishesType.Starters::class.java)
                    data?.let {
                        listOfData.add(data)
                    }
                }
                mutableData.value = listOfData
            }
        }
        return mutableData
    }

    fun getMainCourseMenus(): LiveData<MutableList<DishesType.MainCourses>> {
        val mutableData = MutableLiveData<MutableList<DishesType.MainCourses>>()
        db.collection(ID_MAIN_COURSES).addSnapshotListener { querySnapshot, e ->

            e?.let {
                return@addSnapshotListener
            }

            querySnapshot?.let {
                val dataList = mutableListOf<DishesType.MainCourses>()
                it.documents.forEach { document ->
                    val doc = document.toObject(DishesType.MainCourses::class.java)
                    doc?.let {
                        dataList.add(doc)
                    }
                }
                mutableData.value = dataList
            }
        }
        return mutableData
    }

    fun getDessertMenus(): LiveData<MutableList<DishesType.Desserts>> {
        val mutableData = MutableLiveData<MutableList<DishesType.Desserts>>()

        db.collection(ID_DESSERTS).addSnapshotListener { querySnapshot, e ->

            e?.let {
                return@addSnapshotListener
            }

            querySnapshot?.let {
                val dataList = mutableListOf<DishesType.Desserts>()
                it.documents.forEach { document ->
                    val doc = document.toObject(DishesType.Desserts::class.java)
                    doc?.let {
                        dataList.add(doc)
                    }
                }
                mutableData.value = dataList
            }
        }

        return mutableData
    }
    
}