package be.technifutur.devmob9.projet_cantinapp_android.view.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.model.data.AllergiesModel
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.items.AbstractItem

/**
 * CHANGER ADAPTER EN ITEM
 */

open class AllergiesDetailItem(val allergiesModel: AllergiesModel): AbstractItem<AllergiesDetailItem.AllergiesDetailVH>() {

    override val layoutRes: Int
        get() = R.layout.recyclerview_allergies_item

    override val type: Int
        get() = R.id.recyclerview_allergies_item_id

    override fun getViewHolder(v: View): AllergiesDetailVH = AllergiesDetailVH(v)

    inner class AllergiesDetailVH(v: View): FastAdapter.ViewHolder<AllergiesDetailItem>(v) {

        private val allergiesIllustration: ImageView = v.findViewById(R.id.allergies_illustration)
        private val allergiesName: TextView = v.findViewById(R.id.allergiesName)
        private val allergiesDescription: TextView = v.findViewById(R.id.allergiesDescription)

        override fun bindView(item: AllergiesDetailItem, payloads: List<Any>) {
            item.allergiesModel.allergyIllustration?.let { allergiesIllustration.setImageResource(it) }
            allergiesName.setText(item.allergiesModel.allergyName)
            allergiesDescription.setText(item.allergiesModel.allergyDescription)
            // add allergene title & text here
        }

        override fun unbindView(item: AllergiesDetailItem) {

        }
    }
}