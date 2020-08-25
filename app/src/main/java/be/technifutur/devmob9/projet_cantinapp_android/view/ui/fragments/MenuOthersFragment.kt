package be.technifutur.devmob9.projet_cantinapp_android.view.ui.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
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
import be.technifutur.devmob9.projet_cantinapp_android.model.data.Others
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.FIREBASE_TAG
import be.technifutur.devmob9.projet_cantinapp_android.view.adapter.OthersItemBinder
import be.technifutur.devmob9.projet_cantinapp_android.viewmodel.OthersViewModel
import be.technifutur.devmob9.projet_cantinapp_android.viewmodel.SharedDateViewModel
import be.technifutur.devmob9.projet_cantinapp_android.viewmodel.factory.OthersViewModelFactory
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
    private val othersList = ListSection<Others>()
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
        callback.fragmentTitle("Condiments")
        othersRecyclerView = view.findViewById(R.id.croissant_recycler_view)

        initAdapter()

        othersRecyclerView?.apply {
            this.adapter = othersAdapter
            this.layoutManager = GridLayoutManager(context, 2)
        }

        sharedDateViewModel.sharedDate.observe(viewLifecycleOwner) {
            othersViewModel.fetchingOthers(it)
            othersList.clear()
        }
        fetchingOthers()
    }
    private fun initAdapter() {
        othersAdapter = MultiViewAdapter()
        othersAdapter.registerItemBinders(OthersItemBinder())
        othersAdapter.addSection(othersList)
    }

    private fun fetchingOthers() {
        othersViewModel.fetchedOthers.observe(viewLifecycleOwner) { othersListData ->
            othersListData.forEach {
                othersList.add(it)
                othersAdapter.notifyDataSetChanged()
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