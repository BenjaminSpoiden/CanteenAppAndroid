package be.technifutur.devmob9.projet_cantinapp_android.utils

import android.util.Log
import be.technifutur.devmob9.projet_cantinapp_android.model.CalendarModel
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
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

    fun getRawDateFromFirebaseDoc(){
        db.collection(Constants.COLLECTION_ID).addSnapshotListener { querySnapshot, e ->
            if(e != null) {
                Log.d(Constants.FIREBASE_TAG, "Listen error", e)
                return@addSnapshotListener
            }
            querySnapshot?.let {
                for (document in it.documentChanges) {
                    when (document.type) {
                        DocumentChange.Type.ADDED -> {
                            fillingCalendarDayList(document.document.id, mutableCalendarModelList).forEach {calendarModel ->
                                mutableCalendarModelList.add(calendarModel)
                            }
                            Log.d(Constants.FIREBASE_TAG, "calendarList: $mutableCalendarModelList")
                        }
                        else -> Log.d(Constants.FIREBASE_TAG, "")
                    }
                }
                Log.d(Constants.FIREBASE_TAG, "calendarList: $calendarList")
            }
        }
    }



    private fun fillingCalendarDayList(date: String, mutableList: MutableList<CalendarModel>): List<CalendarModel> {
        mutableList.clear()
        if(checksIfDateIsGood(date)) {
            try {
                val convertToDate = toDateConversion(date)
                mutableList.add(CalendarModel(extractDayNameFromDate(convertToDate), extractDayFromDate(convertToDate)))
            }catch (e: Exception) {
                Log.d(Constants.FIREBASE_TAG, "${e.message}")
            }
        }
        Log.d(Constants.FIREBASE_TAG, "calendarListFromFilling(): ${calendarList.size}")
        return mutableList
    }

    private fun checksIfDateIsGood(date: String) = date.matches("([0-9]{4})-([0-9]{2})-([0-9]{2})".toRegex())
    private fun toDateConversion(date: String) = LocalDate.parse(date, DateTimeFormatter.ofPattern("uuuu-MM-dd", Locale.ROOT).withResolverStyle(ResolverStyle.STRICT))
    private fun extractDayNameFromDate(inputDate: LocalDate) = inputDate.dayOfWeek.name
    private fun extractDayFromDate(inputDate: LocalDate): String {
        val splitter = inputDate.toString().split("-")

        return if(splitter[2].isNotEmpty()){
            splitter[2]
        }else {
            "N/A"
        }
    }
}