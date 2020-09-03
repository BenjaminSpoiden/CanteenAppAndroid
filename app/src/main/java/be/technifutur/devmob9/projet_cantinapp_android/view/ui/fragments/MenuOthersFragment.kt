package be.technifutur.devmob9.projet_cantinapp_android.view.ui.fragments

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.interfaces.FragmentListener
import be.technifutur.devmob9.projet_cantinapp_android.model.data.OthersType
import be.technifutur.devmob9.projet_cantinapp_android.model.data.PlaceholderModel
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.ID_CROISSANTS
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.ID_DRESSINGS
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.ID_DRINKS
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.ID_FRUITS
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.ID_YOGHURTS
import be.technifutur.devmob9.projet_cantinapp_android.view.adapter.MenuHeaderBinder
import be.technifutur.devmob9.projet_cantinapp_android.view.adapter.OthersItemBinder
import be.technifutur.devmob9.projet_cantinapp_android.view.adapter.PlaceholderBinder
import be.technifutur.devmob9.projet_cantinapp_android.viewmodel.CartBadgeViewModel
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
    private lateinit var gridLayoutManager: GridLayoutManager

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

    private val croissantPlaceHolder = ItemSection<PlaceholderModel>()
    private val dressingsPlaceHolder = ItemSection<PlaceholderModel>()
    private val drinksPlaceholder = ItemSection<PlaceholderModel>()
    private val fruitsPlaceholder = ItemSection<PlaceholderModel>()
    private val yoghurtsPlaceholder = ItemSection<PlaceholderModel>()


    private var fragmentListener: FragmentListener? = null

    private val sharedDateViewModel by activityViewModels<SharedDateViewModel>()
    private val cartBadgeViewModel  by activityViewModels<CartBadgeViewModel>()

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

        OthersItemBinder.onItemCLick = { holder ->
            holder.othersCard.isChecked = !holder.othersCard.isChecked
            if(holder.othersCard.isChecked) {
                holder.othersCard.setCardBackgroundColor(resources.getColor(R.color.tameGreen, resources.newTheme()))
                cartBadgeViewModel.onAddingMenuItem(holder.item)
            }else {
                holder.othersCard.setCardBackgroundColor(Color.WHITE)
                cartBadgeViewModel.onDeleteMenuItem(holder.item)
            }
        }



        othersRecyclerView?.apply {
            this.adapter = othersAdapter
            this.layoutManager = gridLayoutManager

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
            fruitsList.clear()
            yoghurtsSection.removeItem()
            yoghurtsList.clear()
            croissantPlaceHolder.removeItem()
            dressingsPlaceHolder.removeItem()
            drinksPlaceholder.removeItem()
            fruitsPlaceholder.removeItem()
            yoghurtsPlaceholder.removeItem()
        }
        fetchingOthers()
    }
    private fun initAdapter() {
        othersAdapter = MultiViewAdapter()
        othersAdapter.setSpanCount(2)
        gridLayoutManager = GridLayoutManager(context, 2).apply {
            this.spanCount = 2
            this.spanSizeLookup = othersAdapter.spanSizeLookup
        }
        othersAdapter.registerItemBinders(OthersItemBinder(requireContext()), MenuHeaderBinder(), PlaceholderBinder())
        setSections()
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

        othersViewModel.checkingEmpty.observe(viewLifecycleOwner) {
            it.forEach {
                when(it) {
                    ID_CROISSANTS -> {
                        croissantSection.setItem("Croissants")
                        croissantPlaceHolder.setItem(PlaceholderModel("Pas de croissants pour aujourd'hui"))
                    }
                    ID_DRESSINGS -> {
                        dressingsSection.setItem("Sauces")
                        dressingsPlaceHolder.setItem(PlaceholderModel("Pas de sauces pour aujourd'hui"))
                    }
                    ID_DRINKS -> {
                        drinksSection.setItem("Boissons")
                        drinksPlaceholder.setItem(PlaceholderModel("Pas de boissons pour aujourd'hui"))
                    }
                    ID_FRUITS -> {
                        fruitsSection.setItem("Fruits")
                        fruitsPlaceholder.setItem(PlaceholderModel("Pas de fruits pour aujourd'hui"))
                    }
                    ID_YOGHURTS -> {
                        yoghurtsSection.setItem("Yaourts")
                        yoghurtsPlaceholder.setItem(PlaceholderModel("Pas de yaourts pour aujourd'hui"))
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
        callback.fragmentTitle("Autres")
    }

    private fun setSections() {
        croissantSection.setSpanCount(1)
        othersAdapter.addSection(croissantSection)
        croissantList.setSpanCount(2)
        othersAdapter.addSection(croissantList)

        croissantPlaceHolder.setSpanCount(1)
        othersAdapter.addSection(croissantPlaceHolder)

        dressingsSection.setSpanCount(1)
        othersAdapter.addSection(dressingsSection)
        dressingsList.setSpanCount(2)
        othersAdapter.addSection(dressingsList)

        dressingsPlaceHolder.setSpanCount(1)
        othersAdapter.addSection(dressingsPlaceHolder)

        drinksSection.setSpanCount(1)
        othersAdapter.addSection(drinksSection)
        drinksList.setSpanCount(2)
        othersAdapter.addSection(drinksList)

        drinksPlaceholder.setSpanCount(1)
        othersAdapter.addSection(drinksPlaceholder)

        fruitsSection.setSpanCount(1)
        othersAdapter.addSection(fruitsSection)
        fruitsList.setSpanCount(2)
        othersAdapter.addSection(fruitsList)

        fruitsPlaceholder.setSpanCount(1)
        othersAdapter.addSection(fruitsPlaceholder)

        yoghurtsSection.setSpanCount(1)
        othersAdapter.addSection(yoghurtsSection)
        yoghurtsList.setSpanCount(2)
        othersAdapter.addSection(yoghurtsList)

        yoghurtsPlaceholder.setSpanCount(1)
        othersAdapter.addSection(yoghurtsPlaceholder)

    }
}