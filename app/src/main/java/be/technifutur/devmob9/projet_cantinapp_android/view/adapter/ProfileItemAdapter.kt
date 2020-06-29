package be.technifutur.devmob9.projet_cantinapp_android.view.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.model.data.AccountItemModel
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.items.AbstractItem

class ProfileItemAdapter(val accountItemModel: AccountItemModel): AbstractItem<ProfileItemAdapter.ProfileItemVH>() {

    override val layoutRes: Int
        get() = R.layout.recyclerview_profil_item

    override val type: Int
        get() = R.id.recyclerview_profil_item_id

    override fun getViewHolder(v: View): ProfileItemVH {
        return ProfileItemVH(v)
    }

    inner class ProfileItemVH(v: View): FastAdapter.ViewHolder<ProfileItemAdapter>(v){

        private val icon: ImageView = v.findViewById(R.id.profil_rv)
        private val type: TextView = v.findViewById(R.id.text_profile)

        override fun bindView(item: ProfileItemAdapter, payloads: List<Any>) {
            icon.setImageResource(item.accountItemModel.icon)
            type.text = item.accountItemModel.type
        }

        override fun unbindView(item: ProfileItemAdapter) {
            type.text = null
        }
    }

}