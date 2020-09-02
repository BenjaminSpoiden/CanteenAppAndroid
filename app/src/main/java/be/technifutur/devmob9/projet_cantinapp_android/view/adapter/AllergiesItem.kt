package be.technifutur.devmob9.projet_cantinapp_android.view.adapter

import android.view.View
import android.widget.ImageView
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.model.data.AllergiesModel
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.items.AbstractItem

class AllergiesItem(val allergyIllustration: Int?): AbstractItem<AllergiesItem.AllergiesViewHolder>() {

    override val layoutRes: Int
        get() = R.layout.recyclerview_allergies_item

    override val type: Int
        get() = R.id.recyclerview_allergies_item_id


    override fun getViewHolder(v: View): AllergiesViewHolder = AllergiesViewHolder(v)

    inner class AllergiesViewHolder(v: View): FastAdapter.ViewHolder<AllergiesItem>(v) {

        private val allergyIllustration: ImageView = v.findViewById(R.id.allergy_image_item)

        override fun bindView(item: AllergiesItem, payloads: List<Any>) {
            item.allergyIllustration?.let { allergyIllustration.setImageResource(it) }
        }

        override fun unbindView(item: AllergiesItem) {
            //
        }
    }
}