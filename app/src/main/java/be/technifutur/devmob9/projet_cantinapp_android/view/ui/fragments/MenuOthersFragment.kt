package be.technifutur.devmob9.projet_cantinapp_android.view.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.interfaces.FragmentListener
import be.technifutur.devmob9.projet_cantinapp_android.model.data.OthersType
import be.technifutur.devmob9.projet_cantinapp_android.view.adapter.MenuHeaderBinder
import be.technifutur.devmob9.projet_cantinapp_android.view.adapter.OthersItemBinder
import be.technifutur.devmob9.projet_cantinapp_android.viewmodel.OthersViewModel
import be.technifutur.devmob9.projet_cantinapp_android.viewmodel.SharedDateViewModel
import be.technifutur.devmob9.projet_cantinapp_android.viewmodel.factory.OthersViewModelFactory
import mva2.adapter.ItemSection
import mva2.adapter.ListSection
import mva2.adapter.MultiViewAdapter
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class MenuOthersFragment: BaseFragment(), KodeinAware {

    companion object {
        fun getInstance() = MenuOthersFragment()
    }

    override val kodein by kodein()
    private val othersViewModelFactory by instance<OthersViewModelFactory>()
    private val othersViewModel by lazy {
        ViewModelProvider(viewModelStore, othersViewModelFactory).get(OthersViewModel::class.java)
    }

    private var othersRecyclerView: RecyclerView? = null
    private lateinit var othersAdapter: MultiViewAdapter

    private val croissantSection = ItemSection<String>()
    private val dressingsSection = ItemSection<String>()
    private val drinksSection = ItemSection<String>()
    private val fruitsSection = ItemSection<String>()
    private val yoghurtsSection = ItemSection<String>()

    private val croissantList = ListSection<OthersType.Croissants>()
    private val dressingsList = ListSection<OthersType.Dressings>()
    private val drinksList = ListSection<OthersType.Drinks>()
    private val fruitsList = ListSection<OthersType.Fruits>()
    private val yoghurtsList = ListSection<OthersType.Yoghurts>()


    private var fragmentListener: FragmentListener? = null

    private val sharedDateViewModel by activityViewModels<SharedDateViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_menu_others, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        othersRecyclerView = view.findViewById(R.id.croissant_recycler_view)

        initAdapter()

        othersRecyclerView?.apply {
            this.adapter = othersAdapter
            this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        }

        sharedDateViewModel.sharedDishesFromDateClick.observe(viewLifecycleOwner) {
            othersViewModel.fetchingOthers(it)
            croissantSection.removeItem()
            croissantList.clear()
            dressingsSection.removeItem()
            dressingsList.clear()
            drinksSection.removeItem()
            drinksList.clear()
            fruitsSection.removeItem()
            drinksList.clear()
            yoghurtsSection.removeItem()
            yoghurtsList.clear()
        }
        fetchingOthers()
    }
    private fun initAdapter() {
        othersAdapter = MultiViewAdapter()
        othersAdapter.registerItemBinders(OthersItemBinder(), MenuHeaderBinder())
        othersAdapter.addSection(croissantSection)
        othersAdapter.addSection(croissantList)
        othersAdapter.addSection(dressingsSection)
        othersAdapter.addSection(dressingsList)
        othersAdapter.addSection(drinksSection)
        othersAdapter.addSection(drinksList)
        othersAdapter.addSection(fruitsSection)
        othersAdapter.addSection(fruitsList)
        othersAdapter.addSection(yoghurtsSection)
        othersAdapter.addSection(yoghurtsList)

    }

    private fun fetchingOthers() {
        othersViewModel.fetchedOthers.observe(viewLifecycleOwner) { othersList ->
            othersList.forEach {
                when(it) {
                    is OthersType.Croissants -> {
                        croissantSection.setItem("Croissants")
                        croissantList.add(it)
                        othersAdapter.notifyDataSetChanged()
                    }
                    is OthersType.Dressings -> {
                        dressingsSection.setItem("Sauces")
                        dressingsList.add(it)
                        othersAdapter.notifyDataSetChanged()
                    }
                    is OthersType.Drinks -> {
                        drinksSection.setItem("Boissons")
                        drinksList.add(it)
                        othersAdapter.notifyDataSetChanged()
                    }
                    is OthersType.Fruits -> {
                        fruitsSection.setItem("Fruits")
                        fruitsList.add(it)
                        othersAdapter.notifyDataSetChanged()
                    }
                    is OthersType.Yoghurts -> {
                        yoghurtsSection.setItem("Yaourts")
                        yoghurtsList.add(it)
                        othersAdapter.notifyDataSetChanged()
                    }
                }
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is FragmentListener) {
            fragmentListener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        fragmentListener = null
    }

    override fun onDestroyView() {
        othersRecyclerView?.apply {
            this.adapter = null
            this.layoutManager = null
        }
        othersRecyclerView = null
        othersAdapter.removeAllSections()
        super.onDestroyView()
    }

    override fun onResume() {
        super.onResume()
        callback.fragmentTitle("Condiments")
    }
}