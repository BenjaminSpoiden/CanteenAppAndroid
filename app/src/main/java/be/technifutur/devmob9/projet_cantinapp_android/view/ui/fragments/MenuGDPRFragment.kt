package be.technifutur.devmob9.projet_cantinapp_android.view.ui.fragments

import android.os.Bundle
import android.view.*
import be.technifutur.devmob9.projet_cantinapp_android.R

class MenuGDPRFragment: BaseFragment() {
    companion object {
        fun getInstance() = MenuGDPRFragment()
    }

    override val title: String
        get() = "Not yet implemented"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_menu_gdpr, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}