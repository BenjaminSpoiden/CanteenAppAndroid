package be.technifutur.devmob9.projet_cantinapp_android.model.data

import kotlinx.android.parcel.Parcelize

@Parcelize
data class Sandwich(
    override val name: String? = null,
    override val description: String? = null,
    override val picture_path: String? = null
): Food()