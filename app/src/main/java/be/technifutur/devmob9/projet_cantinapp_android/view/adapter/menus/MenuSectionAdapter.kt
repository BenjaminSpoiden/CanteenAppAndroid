package be.technifutur.devmob9.projet_cantinapp_android.view.adapter.menus
//
//import android.content.Context
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import android.widget.TextView
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import be.technifutur.devmob9.projet_cantinapp_android.R
//import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants
//import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.TYPE_HEADER
//import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.TYPE_ITEM
//
//class MenuSectionAdapter(private val menuSectionsList: List<MenuSections>, val context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//        return when (viewType) {
//            TYPE_HEADER -> {
//                val headerView = LayoutInflater.from(parent.context).inflate(R.layout.section_menu_row, parent, false)
//                MenuHeaderViewHolder(headerView)
//            }
//            TYPE_ITEM -> {
//                val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_menu_item, parent, false)
//                MenuChildViewHolder(itemView)
//            }
//            else -> {
//                val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_menu_item, parent, false)
//                MenuChildViewHolder(itemView)
//            }
//        }
//    }
//
//    override fun getItemCount(): Int = menuSectionsList.size
//
//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//
//        if(holder is MenuHeaderViewHolder) {
//            val menuSections = menuSectionsList[position]
//            holder.sectionMenuName.text = menuSections.menuSectionName
//        } else if (holder is MenuChildViewHolder) {
//            val menuItem = menuSectionsList[position].menuSectionItems[position]
//
//            holder.menuName.text = menuItem.menuItemName
//            holder.descriptionMenu.text = menuItem.menuDescription
//            menuItem.menuIllustration?.let { holder.illustrationMenu.setImageResource(it) }
//        }
//
////        val menuSections = menuSectionsList[position]
////        val menuChildAdapter = MenuChildAdapter(menuSections.menuSectionItems, context)
////
////        holder.sectionMenuName.text = menuSections.menuSectionName
////        holder.recyclerView.apply {
////            this.adapter = menuChildAdapter
////            this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
////        }
//    }
//
//    override fun getItemViewType(position: Int): Int {
//        return if(position == 0) {
//            TYPE_HEADER
//        } else {
//            TYPE_ITEM
//        }
//    }
//
//    inner class MenuHeaderViewHolder(view: View): RecyclerView.ViewHolder(view) {
//        val sectionMenuName: TextView = view.findViewById(R.id.header_menu_text)
//        val recyclerView: RecyclerView = view.findViewById(R.id.child_menu_recyclerview)
//    }
//
//    inner class MenuChildViewHolder(v: View): RecyclerView.ViewHolder(v) {
//        val menuName: TextView = v.findViewById(R.id.type_menu)
//        val descriptionMenu: TextView = v.findViewById(R.id.menu_description)
//        val illustrationMenu: ImageView = v.findViewById(R.id.menu_illustration)
//    }
//}