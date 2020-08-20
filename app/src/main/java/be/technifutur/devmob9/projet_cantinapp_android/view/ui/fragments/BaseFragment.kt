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
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.viewmodel.MainFragmentViewModel
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

abstract class BaseFragment: Fragment() {

    private lateinit var mainFragmentViewModel: MainFragmentViewModel
    abstract val title: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.isClickable = true
        context?.let {
            ContextCompat.getColor(it, R.color.backgroundAppColor)
        }?.let {
            view.setBackgroundColor(it)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.run {
            mainFragmentViewModel = ViewModelProviders.of(this).get(MainFragmentViewModel::class.java)
        } ?: throw Throwable("Invalid Activity")
        mainFragmentViewModel.onUpdateActionBarTitle(title)
    }
}