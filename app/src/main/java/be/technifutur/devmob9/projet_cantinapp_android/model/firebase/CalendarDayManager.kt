package be.technifutur.devmob9.projet_cantinapp_android.model.firebase

import android.util.Log
import be.technifutur.devmob9.projet_cantinapp_android.interfaces.CalendarListener
import be.technifutur.devmob9.projet_cantinapp_android.model.data.CalendarModel
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.COLLECTION_ID_DAYS
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.FIREBASE_TAG
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.WORKDAY
import com.google.firebase.firestore.*
import com.google.firebase.firestore.EventListener
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.ResolverStyle
import java.lang.Exception
import java.util.*

class CalendarDayManager {

    companion object {
        fun getInstance() =
            CalendarDayManager()
    }

    private val db = FirebaseFirestore.getInstance()
    private val calendarList = ArrayList<CalendarModel>()
    var calendarListener: CalendarListener? = null

    init {
        getFirebaseQuery()
    }

    private fun getFirebaseQuery() {
        db.collection(COLLECTION_ID_DAYS).addSnapshotListener(object : EventListener<QuerySnapshot> {
            override fun onEvent(query: QuerySnapshot?, exception: FirebaseFirestoreException?) {
                if (exception != null) {
                    return
                }
                query?.let { it ->
                    for (dc: DocumentChange in it.documentChanges) {
                        when (dc.type) {
                            DocumentChange.Type.ADDED -> {
                                formattingRawDataToDate(dc.document.id).forEach { calendarModel ->
                                    calendarListener?.onCalendarReceived(
                                        CalendarModel(
                                            calendarModel.dayName,
                                            calendarModel.dayNumber,
                                            dc.document.getBoolean(WORKDAY)
                                        )
                                    )
                                }
                            }
                            else -> Log.d(FIREBASE_TAG, "")
                        }
                    }
                }
            }
        })
    }

    private fun formattingRawDataToDate(date: String): List<CalendarModel> {
        if(checksIfDateIsGood(date)) {
            calendarList.clear()
            try {

                /**
                 * Regarder pour les traductions des jours en fonction du langage du téléphone
                 */

                val toDateConversion = LocalDate.parse(date, DateTimeFormatter.ofPattern("uuuu-MM-dd", Locale.ROOT).withResolverStyle(ResolverStyle.STRICT))

                val toDayOfWeekConversion = toDateConversion.dayOfWeek.name

                //TOUT KAKER (optmisation)
                val toDayNumberConversion = toDateConversion.toString().split("-")

                /**
                 * Les dates sont de type "2020-01-01", donc le split permet de séparer en année/mois/jours.
                 * Le toDayNumberConversion[2] permet de reprendre le jour.
                 */

                if(toDayNumberConversion[2].isNotEmpty()) {
                    calendarList.add(
                        CalendarModel(
                            toDayOfWeekConversion,
                            toDayNumberConversion[2]
                        )
                    )
                }
            }catch (e: Exception) {
                Log.d(FIREBASE_TAG, "${e.cause}")
            }
        }
        return calendarList
    }

    private fun checksIfDateIsGood(date: String) = date.matches("([0-9]{4})-([0-9]{2})-([0-9]{2})".toRegex())
}