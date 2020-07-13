package be.technifutur.devmob9.projet_cantinapp_android.view.adapter

import android.view.View
import android.widget.ImageView
import be.technifutur.devmob9.projet_cantinapp_android.R
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.items.AbstractItem

class AllergyTierHighItem(val allergyTierImage: Int): AbstractItem<AllergyTierHighItem.AllergyTierVH>() {

    override val layoutRes: Int
        get() = R.layout.recyclerview_allergy_tier_item

    override val type: Int
        get() = R.id.recyclerview_allergy_tier_item_id

    override fun getViewHolder(v: View): AllergyTierVH {
        return AllergyTierVH(v)
    }

    inner class AllergyTierVH(v: View): FastAdapter.ViewHolder<AllergyTierHighItem>(v) {

        private val allergyImage: ImageView = v.findViewById(R.id.allergy_tier_item)

        override fun bindView(item: AllergyTierHighItem, payloads: List<Any>) {
            allergyImage.setImageResource(item.allergyTierImage)
        }

        override fun unbindView(item: AllergyTierHighItem) {

        }
    }
}