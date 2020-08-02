package be.technifutur.devmob9.projet_cantinapp_android.view.adapter.menus

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import be.technifutur.devmob9.projet_cantinapp_android.R

class MenuSectionAdapter(private val menuSectionsList: List<MenuSections>, val context: Context): RecyclerView.Adapter<MenuSectionAdapter.MenuSectionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuSectionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.section_menu_row, parent, false)
        return MenuSectionViewHolder(view)
    }

    override fun getItemCount(): Int = menuSectionsList.size

    override fun onBindViewHolder(holder: MenuSectionViewHolder, position: Int) {
        val menuSections = menuSectionsList[position]
        val menuChildAdapter = MenuChildAdapter(menuSections.menuSectionItems, context)

        holder.sectionMenuName.text = menuSections.menuSectionName
        holder.recyclerView.apply {
            this.adapter = menuChildAdapter
            this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

    inner class MenuSectionViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val sectionMenuName: TextView = view.findViewById(R.id.header_menu_text)
        val recyclerView: RecyclerView = view.findViewById(R.id.child_menu_recyclerview)
    }
}