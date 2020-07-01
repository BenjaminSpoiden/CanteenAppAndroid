package be.technifutur.devmob9.projet_cantinapp_android.view.adapter

import android.view.View
import android.widget.ImageView
import be.technifutur.devmob9.projet_cantinapp_android.R
import com.google.android.material.card.MaterialCardView
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.items.AbstractItem

class AllergiesSelectionAdapter(val illustration: Int): AbstractItem<AllergiesSelectionAdapter.AllergiesSelectionVH>() {

    override val layoutRes: Int
        get() = R.layout.recyclerview_allergies_selection

    override val type: Int
        get() = R.id.recyclerview_allergies_selection_id

    override fun getViewHolder(v: View): AllergiesSelectionVH {
        return AllergiesSelectionVH(v)
    }

    inner class AllergiesSelectionVH(v: View): FastAdapter.ViewHolder<AllergiesSelectionAdapter>(v) {

        private val allergiesSelectionImage: ImageView = v.findViewById(R.id.rv_allergies_selection_image)

        override fun bindView(item: AllergiesSelectionAdapter, payloads: List<Any>) {
            allergiesSelectionImage.setImageResource(item.illustration)
        }

        override fun unbindView(item: AllergiesSelectionAdapter) {

        }
    }
}