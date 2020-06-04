package be.technifutur.devmob9.projet_cantinapp_android.utils

import android.util.Log
import be.technifutur.devmob9.projet_cantinapp_android.model.CalendarModel
import be.technifutur.devmob9.projet_cantinapp_android.model.firebase.FirebaseSource
import be.technifutur.devmob9.projet_cantinapp_android.view.adapter.CalendarAdapter
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.ResolverStyle
import java.lang.Exception
import java.util.*

class CalendarDayManager {

    private val db = FirebaseFirestore.getInstance()

    companion object {
        fun getInstance() = CalendarDayManager()
    }
    private val mutableCalendarModelList: MutableList<CalendarModel> = ArrayList()
    val calendarList: List<CalendarModel>
        get() = mutableCalendarModelList

    fun getRawDateFromFirebaseDoc(): ListenerRegistration{
       return db.collection(FirebaseSource.COLLECTION_ID).addSnapshotListener { querySnapshot, e ->
            if(e != null) {
                Log.d(FirebaseSource.TAG, "Listen error", e)
                return@addSnapshotListener
            }

            querySnapshot?.let {
                for (document in it.documentChanges) {
                    when (document.type) {
                        DocumentChange.Type.ADDED -> {
                            fillingCalendarDayList(document.document.id, mutableCalendarModelList)
                        }
                        else -> Log.d(FirebaseSource.TAG, "")
                    }
                }
                Log.d(FirebaseSource.TAG, "calendarList: $calendarList")
            }
        }
    }

    private fun fillingCalendarDayList(date: String, mutableList: MutableList<CalendarModel>): List<CalendarModel> {
        if(checksIfDateIsGood(date)) {
            mutableList.clear()
            try {
                val convertToDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("uuuu-MM-dd", Locale.ROOT).withResolverStyle(ResolverStyle.STRICT))
                val convertedToDayOfWeek = convertToDate.dayOfWeek.name
                val convertedToDayNumber = convertToDate.toString().split("-")
                if(convertedToDayNumber[2].isNotEmpty()) {
                    mutableList.add(CalendarModel(convertedToDayOfWeek, convertedToDayNumber[2]))
                }

            }catch (e: Exception) {
                Log.d(FirebaseSource.TAG, "${e.message}")
            }
        }
        mutableList.forEach {
            Log.d(FirebaseSource.TAG, "New Date from fillingCalendar(Bingo le gogo): $it")
        }
        return mutableList
    }
    private fun checksIfDateIsGood(date: String) = date.matches("([0-9]{4})-([0-9]{2})-([0-9]{2})".toRegex())
}