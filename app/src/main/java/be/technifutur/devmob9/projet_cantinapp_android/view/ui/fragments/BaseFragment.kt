package be.technifutur.devmob9.projet_cantinapp_android.view.ui.fragments

/**
 * FAIRE COMME TELEGRAM !
 * VERY IMPORTANT
 */

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.interfaces.FragmentListener
import be.technifutur.devmob9.projet_cantinapp_android.viewmodel.MainFragmentViewModel

abstract class BaseFragment: Fragment() {

    abstract val title: String

//    private var listener: FragmentListener? = null

    private lateinit var mainFragmentViewModel: MainFragmentViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.isClickable = true
        context?.let {
            ContextCompat.getColor(it, R.color.backgroundAppColor)
        }?.let {
            view.setBackgroundColor(it)
        }
//        listener?.getFragmentTitle(title)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.run {
            mainFragmentViewModel = ViewModelProviders.of(this).get(MainFragmentViewModel::class.java)
        } ?: throw Throwable("Invalid Activity")
        mainFragmentViewModel.onUpdateActionBarTitle(title)
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("Attached", "onAttach from $context")
    }

//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        if(context is FragmentListener){
//            listener = context
//        }
//    }
//
//    override fun onDetach() {
//        super.onDetach()
//        listener = null
//    }
}