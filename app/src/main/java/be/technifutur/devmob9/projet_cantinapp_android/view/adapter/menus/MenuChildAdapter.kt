package be.technifutur.devmob9.projet_cantinapp_android.view.adapter.menus

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.interfaces.FragmentListener
import be.technifutur.devmob9.projet_cantinapp_android.interfaces.ItemSelectedListener
import be.technifutur.devmob9.projet_cantinapp_android.model.data.MenuItemModel
import be.technifutur.devmob9.projet_cantinapp_android.view.ui.activities.MainActivity
import com.google.android.material.card.MaterialCardView
import kotlinx.android.synthetic.main.recyclerview_menu_item.view.*

class MenuChildAdapter(private val menuItemList: List<MenuItemModel>, context: Context): RecyclerView.Adapter<MenuChildAdapter.MenuChildViewHolder>() {

    private val openDetailFragmentListener: FragmentListener = context as FragmentListener
    private var itemSelectedListener: ItemSelectedListener = context as ItemSelectedListener

    private var isSelected: Boolean = false


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuChildViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_menu_item, parent, false)
        return MenuChildViewHolder(view).also {
            view.menu_detail_button.setOnClickListener {_ ->
                openDetailFragmentListener.onDetailFragmentClick(it)
            }
        }.also {
            view.setOnClickListener {v ->
                val check = v?.findViewById<ImageView>(R.id.selected_menu_item)
                val menuCardView = v?.findViewById<MaterialCardView>(R.id.menu_bg)

                if (check != null && menuCardView != null) {
                    this.isSelected = !this.isSelected
                    isMenuItemSelected(isSelected, check, menuCardView)
                    itemSelectedListener.onNumberItemSelected(45)
                }
            }
        }
    }

    override fun getItemCount(): Int = menuItemList.size

    override fun onBindViewHolder(holder: MenuChildViewHolder, position: Int) {
        val menuItem = menuItemList[position]

        holder.menuName.text = menuItem.menuItemName
        holder.descriptionMenu.text = menuItem.menuDescription
        menuItem.menuIllustration?.let { holder.illustrationMenu.setImageResource(it) }
    }

    override fun onViewRecycled(holder: MenuChildViewHolder) {
        super.onViewRecycled(holder)
        holder.menuName.text = null
        holder.descriptionMenu.text = null

    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        Log.d("ClickEvent", "onAttachedToRecyclerView()")
    }

    private fun isMenuItemSelected(isSelected: Boolean, check: ImageView, background: MaterialCardView) {
        if(isSelected) {
            check.visibility = View.VISIBLE
            background.setCardBackgroundColor(Color.parseColor("#DBF9D8"))
        }else {
            check.visibility = View.INVISIBLE
            background.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
        }
    }

    inner class MenuChildViewHolder(v: View): RecyclerView.ViewHolder(v) {
        val menuName: TextView = v.findViewById(R.id.type_menu)
        val descriptionMenu: TextView = v.findViewById(R.id.menu_description)
        val illustrationMenu: ImageView = v.findViewById(R.id.menu_illustration)
    }


}