package be.technifutur.devmob9.projet_cantinapp_android.view.ui

import android.icu.text.DateFormat
import android.icu.util.Calendar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.databinding.FragmentMainMenuBinding
import be.technifutur.devmob9.projet_cantinapp_android.model.CalendarModel
import be.technifutur.devmob9.projet_cantinapp_android.view.adapter.CalendarAdapter
import be.technifutur.devmob9.projet_cantinapp_android.viewmodel.AuthViewModel
import be.technifutur.devmob9.projet_cantinapp_android.viewmodel.AuthViewModelFactory
import kotlinx.android.synthetic.main.fragment_main_menu.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance
import java.text.FieldPosition
import java.text.ParsePosition
import java.util.*


class MainMenuFragment: Fragment(), KodeinAware {

    override val kodein by kodein()
    private val factory: AuthViewModelFactory by instance<AuthViewModelFactory>()
    private lateinit var viewModel: AuthViewModel
    private lateinit var calendarRecyclerView: RecyclerView
    private lateinit var calendarAdapter: CalendarAdapter
    private lateinit var calendarModelList: ArrayList<CalendarModel>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentMainMenuBinding>(inflater, R.layout.fragment_main_menu, container, false)
        viewModel = ViewModelProviders.of(this, factory).get(AuthViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bottomBar.onItemSelected = {
            when(it) {
                0 -> Toast.makeText(context, "", Toast.LENGTH_SHORT).show()
                1 -> Toast.makeText(context, "", Toast.LENGTH_SHORT).show()
                2 -> Toast.makeText(context, "", Toast.LENGTH_SHORT).show()
                3 -> Toast.makeText(context, "", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        view?.let {
            calendarRecyclerView = it.findViewById(R.id.calendar_recyclerview)
        }
        calendarAdapter = CalendarAdapter(fillingCalendarList())
        calendarRecyclerView.adapter = calendarAdapter
        calendarRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun fillingCalendarList(): List<CalendarModel> {
        calendarModelList = ArrayList<CalendarModel>()
        calendarModelList.add(CalendarModel("LUNDI", "01"))
        calendarModelList.add(CalendarModel("MARDI", "02"))
        calendarModelList.add(CalendarModel("MERCREDI", "01"))
        calendarModelList.add(CalendarModel("JEUDI", "02"))
        calendarModelList.add(CalendarModel("VENDREDI", "01"))
        calendarModelList.add(CalendarModel("LUNDI", "02"))
        calendarModelList.add(CalendarModel("MARDI", "01"))
        calendarModelList.add(CalendarModel("MARDI", "02"))
        return calendarModelList
    }
}