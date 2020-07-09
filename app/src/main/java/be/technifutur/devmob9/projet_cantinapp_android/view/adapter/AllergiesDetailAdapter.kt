package be.technifutur.devmob9.projet_cantinapp_android.view.adapter

import android.view.View
import android.widget.ImageView
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.model.data.AllergiesModel
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.items.AbstractItem

class AllergiesDetailAdapter(val allergiesModel: AllergiesModel): AbstractItem<AllergiesDetailAdapter.AllergiesDetailVH>() {

    override val layoutRes: Int
        get() = R.layout.recyclerview_allergie_info

    override val type: Int
        get() = R.id.recyclerview_allergies_detail_item_id

    override fun getViewHolder(v: View): AllergiesDetailVH = AllergiesDetailVH(v)

    inner class AllergiesDetailVH(v: View): FastAdapter.ViewHolder<AllergiesDetailAdapter>(v) {

        private val allergiesIllustration: ImageView = v.findViewById(R.id.allergies_illustration)

        override fun bindView(item: AllergiesDetailAdapter, payloads: List<Any>) {
            allergiesIllustration.setImageResource(item.allergiesModel.allergyIllustration)
        }

        override fun unbindView(item: AllergiesDetailAdapter) {

        }
    }
}