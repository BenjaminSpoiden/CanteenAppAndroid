package be.technifutur.devmob9.projet_cantinapp_android.view.adapter

import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.model.data.AllergiesModel

class AllergiesDetailItemRightLayout(allergiesModel: AllergiesModel) : AllergiesDetailItem(allergiesModel) {

    override val layoutRes: kotlin.Int
        get() = R.layout.recyclerview_allergie_info_right_allignement

    override val type: kotlin.Int
        get() = R.id.recyclerview_allergies_right_detail_item_id

}