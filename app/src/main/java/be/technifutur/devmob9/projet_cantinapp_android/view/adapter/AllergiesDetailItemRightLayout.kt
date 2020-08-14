package be.technifutur.devmob9.projet_cantinapp_android.view.adapter

import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.model.data.AllergiesModel

class AllergiesDetailItemRightLayout(allergiesModel: AllergiesModel) : AllergiesDetailItem(allergiesModel) {

    override val layoutRes: Int
        get() = R.layout.recyclerview_allergie_info_right_allignement

}