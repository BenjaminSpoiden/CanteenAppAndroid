package be.technifutur.devmob9.projet_cantinapp_android.view.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.interfaces.FragmentListener
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment: BaseFragment() {
    companion object {
        fun getInstance() = MainFragment()
    }
    override val title: String
        get() = ""

    private var fragmentListener: FragmentListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bottomBar.onItemSelected = {
            fragmentListener?.onFragmentSelectedFromMenu(it)
        }
    }

    override fun onStart() {
        super.onStart()
        initFirstFragment()
    }

    private fun initFirstFragment(){
        fragmentListener?.onFragmentSelectedFromMenu(0)
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
}