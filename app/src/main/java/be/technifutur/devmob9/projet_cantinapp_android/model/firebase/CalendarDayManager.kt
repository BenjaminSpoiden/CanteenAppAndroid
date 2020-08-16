package be.technifutur.devmob9.projet_cantinapp_android.model.firebase

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import be.technifutur.devmob9.projet_cantinapp_android.model.data.CalendarModel
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.COLLECTION_ID_DAYS
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.FIREBASE_TAG
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.WORKDAY
import com.google.firebase.firestore.*
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.ResolverStyle
import org.threeten.bp.format.TextStyle
import java.lang.Exception
import java.util.*

class CalendarDayManager() {


    private val db = FirebaseFirestore.getInstance()
    private val calendarList = ArrayList<CalendarModel>()

    fun getCalendarDays(): LiveData<MutableList<CalendarModel>> {
        val mutableDaysData = MutableLiveData<MutableList<CalendarModel>>()
        db.collection(COLLECTION_ID_DAYS)
            .addSnapshotListener { query, e ->
            e?.let {
                return@addSnapshotListener
            }
            query?.let {
                val listOfDaysData = mutableListOf<CalendarModel>()
                it.documents.forEach { document ->
                    formattingRawDataToDate(document.id).forEach { calendar ->
                        listOfDaysData.add(CalendarModel(calendar.dayName, calendar.dayNumber, calendar.date, document.getBoolean(WORKDAY)))
                    }
                }
                mutableDaysData.value = listOfDaysData
            }
        }
        return mutableDaysData
    }

    private fun formattingRawDataToDate(date: String): List<CalendarModel> {
        if(checksIfDateIsGood(date)) {
            calendarList.clear()
            try {

                /**
                 * Regarder pour les traductions des jours en fonction du langage du téléphone
                 */

                val toDateConversion = LocalDate.parse(date, DateTimeFormatter.ofPattern("uuuu-MM-dd", Locale.FRENCH).withResolverStyle(ResolverStyle.STRICT))

                val toDayOfWeekConversion = toDateConversion.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.FRENCH)

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
                            toDayNumberConversion[2],
                            toDateConversion
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