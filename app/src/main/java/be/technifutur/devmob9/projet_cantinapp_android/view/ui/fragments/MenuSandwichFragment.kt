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
import be.technifutur.devmob9.projet_cantinapp_android.model.data.Sandwich
import be.technifutur.devmob9.projet_cantinapp_android.view.adapter.SandwichItemBinder
import be.technifutur.devmob9.projet_cantinapp_android.viewmodel.CartBadgeViewModel
import be.technifutur.devmob9.projet_cantinapp_android.viewmodel.SandwichViewModel
import be.technifutur.devmob9.projet_cantinapp_android.viewmodel.SharedDateViewModel
import be.technifutur.devmob9.projet_cantinapp_android.viewmodel.factory.SandwichViewModelFactory
import mva2.adapter.ListSection
import mva2.adapter.MultiViewAdapter
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class MenuSandwichFragment: BaseFragment(), KodeinAware {

    companion object {
        fun getInstance() = MenuSandwichFragment()
    }

    override val kodein by kodein()
    private val sandwichViewModelFactory by instance<SandwichViewModelFactory>()
    private val sandwichViewModel by lazy {
        ViewModelProvider(viewModelStore, sandwichViewModelFactory).get(SandwichViewModel::class.java)
    }

    private var sandwichRecyclerView: RecyclerView? = null
    private lateinit var sandwichAdapter: MultiViewAdapter
    private val sandwichList = ListSection<Sandwich>()
    private var fragmentListener: FragmentListener? = null

    private val sharedDateViewModel by activityViewModels<SharedDateViewModel>()
    private val cartBadgeViewModel  by activityViewModels<CartBadgeViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_menu_sandwich, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        callback.fragmentTitle("Sandwich")
        sandwichRecyclerView = view.findViewById(R.id.sandwich_recycler_view)

        initAdapter()

        sandwichRecyclerView?.apply {
            this.adapter = sandwichAdapter
            this.layoutManager = GridLayoutManager(context, 2)
        }

        sharedDateViewModel.sharedDishesFromDateClick.observe(viewLifecycleOwner) {
            onRefreshList()
            sandwichViewModel.fetchingSandwiches(it)
        }
        fetchingSandwiches()
    }

    private fun fetchingSandwiches() {
        sandwichViewModel.fetchedSandwiches.observe(viewLifecycleOwner) { sandwichListData ->
            sandwichListData.forEach {
                sandwichList.add(it)
                sandwichAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun initAdapter() {
        sandwichAdapter = MultiViewAdapter()
        sandwichAdapter.registerItemBinders(SandwichItemBinder {
            it.sandwichCard.isChecked = !it.sandwichCard.isChecked
            if(it.sandwichCard.isChecked) {
                it.sandwichCard.setCardBackgroundColor(resources.getColor(R.color.tameGreen, resources.newTheme()))
                cartBadgeViewModel.onAddingMenuItem(it.item)
            }else {
                it.sandwichCard.setCardBackgroundColor(Color.WHITE)
                cartBadgeViewModel.onDeleteMenuItem(it.item)
            }
        })
        sandwichAdapter.addSection(sandwichList)
    }

    private fun onRefreshList() {
        sandwichList.clear()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is FragmentListener){
            fragmentListener = context
        }

    }

    override fun onDetach() {
        super.onDetach()
        fragmentListener = null
    }

    override fun onDestroyView() {
        sandwichRecyclerView?.apply {
            this.adapter = null
            this.layoutManager = null
        }
        sandwichAdapter.removeAllSections()
        sandwichRecyclerView = null
        super.onDestroyView()
    }

    override fun onResume() {
        super.onResume()
        callback.fragmentTitle("Sandwich")
    }
}