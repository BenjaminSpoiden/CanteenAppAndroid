package be.technifutur.devmob9.projet_cantinapp_android.view.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import be.technifutur.devmob9.projet_cantinapp_android.R
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.items.AbstractItem

class AllergySelectionItem(val allergySelectionName: String, val allergySelectionIllustration: Int): AbstractItem<AllergySelectionItem.AllergyTierVH>() {

    override val layoutRes: Int
        get() = R.layout.recyclerview_allergy_selection_item

    override val type: Int
        get() = R.id.recyclerview_allergy_selection_item_id

    override fun getViewHolder(v: View): AllergyTierVH {
        return AllergyTierVH(v)
    }

    inner class AllergyTierVH(v: View): FastAdapter.ViewHolder<AllergySelectionItem>(v) {

        private val allergyName: TextView = v.findViewById(R.id.allergy_selection_item_name)
        private val allergyImage: ImageView = v.findViewById(R.id.allergy_selection_item_illustration)

        override fun bindView(item: AllergySelectionItem, payloads: List<Any>) {
            allergyName.text = item.allergySelectionName
            allergyImage.setImageResource(item.allergySelectionIllustration)
        }

        override fun unbindView(item: AllergySelectionItem) {
            allergyName.text = null
        }
    }
}