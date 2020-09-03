package be.technifutur.devmob9.projet_cantinapp_android.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.model.data.CalendarModel
import be.technifutur.devmob9.projet_cantinapp_android.utils.colorSelection
import com.google.android.material.card.MaterialCardView
import org.threeten.bp.LocalDate

class CalendarAdapter: RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder>() {

    private val calendarModelList = ArrayList<CalendarModel>()

    companion object {
        var onDateListener: ((LocalDate?) -> Unit)? = null
    }

    fun addAll(calendarList: List<CalendarModel>){
        calendarModelList.addAll(calendarList)
        notifyDataSetChanged()
    }

    fun get(position: Int): CalendarModel{
        return calendarModelList[position]
    }

    fun getPositionFromItem(calendarModel: CalendarModel): Int {
        checkedPosition = calendarModelList.lastIndexOf(calendarModel)
        return calendarModelList.lastIndexOf(calendarModel)
    }

    private var checkedPosition = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.calendar_item, parent, false)
        return CalendarViewHolder(view)
    }

    override fun getItemCount(): Int {
        return calendarModelList.size
    }

    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        holder.bind(calendarModelList[position])

        holder.calendarCard.setOnClickListener {
            setCardState(holder.calendarCard, holder.calendarDayName, holder.calendarDayNumber, true)
            holder.calendarCard.isEnabled = false
            if(checkedPosition != holder.adapterPosition){
                notifyItemChanged(checkedPosition)
                checkedPosition = holder.adapterPosition
            }
            onDateListener?.invoke(calendarModelList[position].date)
        }
    }

    inner class CalendarViewHolder(v: View): RecyclerView.ViewHolder(v) {
        val calendarDayName: TextView = v.findViewById(R.id.day_tv)
        val calendarDayNumber: TextView = v.findViewById(R.id.day_number_tv)
        val calendarCard: MaterialCardView = v.findViewById(R.id.calendar_card_view)

        fun bind(calendarModel: CalendarModel) {
            calendarDayName.text = calendarModel.dayName
            calendarDayNumber.text = calendarModel.dayNumber
            calendarModel.isWorkday?.let {
                if(!it) {
                    calendarCard.alpha = 0.5F
                    calendarCard.isEnabled = false
                    calendarCard.isClickable = false
                    calendarCard.isFocusable = false
                }
            }

            if(checkedPosition == -1){
                setCardState(calendarCard, calendarDayName, calendarDayNumber, false)
                calendarCard.isEnabled = true
            }else {
                if(checkedPosition == adapterPosition){
                    setCardState(calendarCard, calendarDayName, calendarDayNumber, true)
                    calendarCard.isEnabled = false
                }else {
                    setCardState(calendarCard, calendarDayName, calendarDayNumber, false)
                    calendarCard.isEnabled = true
                }
            }
        }
    }

    private fun setCardState(materialCardView: MaterialCardView, dayName: TextView, dayNumber: TextView, boolean: Boolean){
        if(boolean) {
            materialCardView.setCardBackgroundColor(colorSelection("#DBF9D8"))
            materialCardView.cardElevation = 10F
            dayName.setTextColor(colorSelection("#2F4858"))
            dayNumber.setTextColor(colorSelection("#2F4858"))
        }else {
            materialCardView.setCardBackgroundColor(colorSelection("#FFFFFF"))
            dayName.setTextColor(colorSelection("#37BA89"))
            materialCardView.cardElevation = 0F
            dayNumber.setTextColor(colorSelection("#37BA89"))
        }
    }
}