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

class MainFragment: Fragment() {

    companion object {
        fun getInstance() = MainFragment()
    }

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

    /**
     * Calling [MenuRepasFragment] at the start of this fragment
     */
    override fun onStart() {
        super.onStart()
        activity?.let {
            it.supportFragmentManager.beginTransaction().add(R.id.fragment_container_main, HomeFragment.getInstance()).commit()
        }
        fragmentListener?.onFragmentSelectedFromMenu(0)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is FragmentListener){
            Log.d(Constants.CONTEXT_TEST, "onAttach()")
            fragmentListener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        fragmentListener = null
        Log.d(Constants.CONTEXT_TEST, "onDetach()")
    }
}