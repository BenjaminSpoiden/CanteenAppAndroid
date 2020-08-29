package be.technifutur.devmob9.projet_cantinapp_android.model.data

import kotlinx.android.parcel.Parcelize


sealed class DishesType: Food() {
    @Parcelize
    data class Starters(
        override val name: String? = null,
        override val description: String? = null,
        override val price: Int? = null,
        override val energy: Int? = null,
        override val sugar: Int? = null,
        override val lipids: Int? = null,
        override val proteins: Int? = null,
        override val picture_path: String? = null): DishesType()
    @Parcelize
    data class MainCourses(
        override val name: String? = null,
        override val description: String? = null,
        override val price: Int? = null,
        override val energy: Int? = null,
        override val sugar: Int? = null,
        override val lipids: Int? = null,
        override val proteins: Int? = null,
        override val picture_path: String? = null): DishesType()
    @Parcelize
    data class Desserts(
        override val name: String? = null,
        override val description: String? = null,
        override val price: Int? = null,
        override val energy: Int? = null,
        override val sugar: Int? = null,
        override val lipids: Int? = null,
        override val proteins: Int? = null,
        override val picture_path: String? = null): DishesType()
}
