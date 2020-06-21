package be.technifutur.devmob9.projet_cantinapp_android.view.ui.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.interfaces.FragmentListener
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment: BaseFragment() {

    companion object {
        fun getInstance() = MainFragment()
    }

    private var fragmentListener: FragmentListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFirstFragment()
        bottomBar.onItemSelected = {
            fragmentListener?.onFragmentSelectedFromMenu(it)
        }
    }

    /**
     * Calling [MenuRepasFragment] at the start of this fragment
     */
    private fun initFirstFragment(){
        super.onStart()
        activity?.let {
            it.supportFragmentManager.beginTransaction().add(R.id.fragment_container_main, HomeFragment.getInstance()).commit()
        }
        fragmentListener?.onFragmentSelectedFromMenu(0)
    }

//    override fun onStart() {
//        super.onStart()
//        activity?.let {
//            it.supportFragmentManager.beginTransaction().add(R.id.fragment_container_main, HomeFragment.getInstance()).commit()
//        }
//        fragmentListener?.onFragmentSelectedFromMenu(0)
//    }

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