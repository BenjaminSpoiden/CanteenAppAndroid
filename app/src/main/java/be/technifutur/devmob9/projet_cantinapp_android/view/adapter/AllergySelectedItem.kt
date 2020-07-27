package be.technifutur.devmob9.projet_cantinapp_android.view.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.model.data.AllergiesSelectedModel
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.items.AbstractItem

class AllergySelectedItem(val allergySelectionName: String, val allergySelectionIllustration: Int): AbstractItem<AllergySelectedItem.AllergiesSelectionVH>() {

    override val layoutRes: Int
        get() = R.layout.recyclerview_allergy_selected_item

    override val type: Int
        get() = R.id.recyclerview_allergy_selected_item_id

    override fun getViewHolder(v: View): AllergiesSelectionVH {
        return AllergiesSelectionVH(v)
    }

    inner class AllergiesSelectionVH(v: View): FastAdapter.ViewHolder<AllergySelectedItem>(v) {

        private val allergySelectedName: TextView = v.findViewById(R.id.allergy_selected_item_name)
        private val allergySelectedImage: ImageView = v.findViewById(R.id.allergy_selected_item_illustration)

        override fun bindView(item: AllergySelectedItem, payloads: List<Any>) {
            allergySelectedName.text = item.allergySelectionName
            allergySelectedImage.setImageResource(item.allergySelectionIllustration)
        }

        override fun unbindView(item: AllergySelectedItem) {
            allergySelectedName.text = null
        }
    }
}