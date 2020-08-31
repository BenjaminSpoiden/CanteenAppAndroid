package be.technifutur.devmob9.projet_cantinapp_android.model.data

import kotlinx.android.parcel.Parcelize

sealed class OthersType: Food() {
    @Parcelize
    data class Croissants(
        override val name: String? = null,
        override val description: String? = null,
        override val price: Int? = null,
        override val energy: Int? = null,
        override val sugar: Int? = null,
        override val lipids: Int? = null,
        override val proteins: Int? = null,
        override val picture_path: String? = null
    ): OthersType()

    @Parcelize
    data class Dressings(
        override val name: String? = null,
        override val description: String? = null,
        override val price: Int? = null,
        override val energy: Int? = null,
        override val sugar: Int? = null,
        override val lipids: Int? = null,
        override val proteins: Int? = null,
        override val picture_path: String? = null
    ): OthersType()

    @Parcelize
    data class Drinks(
        override val name: String? = null,
        override val description: String? = null,
        override val price: Int? = null,
        override val energy: Int? = null,
        override val sugar: Int? = null,
        override val lipids: Int? = null,
        override val proteins: Int? = null,
        override val picture_path: String? = null
    ): OthersType()

    @Parcelize
    data class Fruits(
        override val name: String? = null,
        override val description: String? = null,
        override val price: Int? = null,
        override val energy: Int? = null,
        override val sugar: Int? = null,
        override val lipids: Int? = null,
        override val proteins: Int? = null,
        override val picture_path: String? = null
    ): OthersType()

    @Parcelize
    data class Yoghurts(
        override val name: String? = null,
        override val description: String? = null,
        override val price: Int? = null,
        override val energy: Int? = null,
        override val sugar: Int? = null,
        override val lipids: Int? = null,
        override val proteins: Int? = null,
        override val picture_path: String? = null
    ): OthersType()
}