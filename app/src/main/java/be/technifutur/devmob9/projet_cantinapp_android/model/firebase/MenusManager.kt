package be.technifutur.devmob9.projet_cantinapp_android.model.firebase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import be.technifutur.devmob9.projet_cantinapp_android.model.data.DishesType
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.ID_DESSERTS
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.ID_MAIN_COURSES
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.ID_STARTERS
import com.google.firebase.firestore.FirebaseFirestore

class MenusManager {
    private val db = FirebaseFirestore.getInstance()

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