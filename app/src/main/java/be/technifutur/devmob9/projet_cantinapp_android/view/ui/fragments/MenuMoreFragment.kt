package be.technifutur.devmob9.projet_cantinapp_android.view.ui.fragments

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.view.ui.activities.MainActivity
import kotlinx.android.synthetic.main.activity_main.*

class MenuMoreFragment: BaseFragment() {
    companion object {
        fun getInstance() = MenuMoreFragment()
    }

    override val title: String
        get() = "Detail"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_menu_more, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        activity?.actionBar?.setDisplayShowTitleEnabled(false)

    }
}