package be.technifutur.devmob9.projet_cantinapp_android.view.adapter

import android.view.View
import android.widget.ImageView
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.model.data.AllergiesModel
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.items.AbstractItem

class AllergiesItemAdapter(val allergiesModel: AllergiesModel): AbstractItem<AllergiesItemAdapter.AllergiesViewHolder>() {

    override val layoutRes: Int
        get() = R.layout.recyclerview_allergies_item

    override val type: Int
        get() = R.id.recyclerview_allergies_item_id


    override fun getViewHolder(v: View): AllergiesViewHolder = AllergiesViewHolder(v)

    inner class AllergiesViewHolder(v: View): FastAdapter.ViewHolder<AllergiesItemAdapter>(v) {

        private val allergyIllustration: ImageView = v.findViewById(R.id.allergy_image_item)

        override fun bindView(item: AllergiesItemAdapter, payloads: List<Any>) {
            allergyIllustration.setImageResource(item.allergiesModel.allergyIllustration)
        }

        override fun unbindView(item: AllergiesItemAdapter) {

        }
    }
}