package be.technifutur.devmob9.projet_cantinapp_android.model.data

sealed class DishesType: Food() {
    data class Starters(
        val name: String? = null,
        val description: String? = null,
        val price: Int? = null,
        val energy: Int? = null,
        val sugar: Int? = null,
        val fat: Int? = null,
        val protein: Int? = null,
        val picture_path: String? = null): DishesType()

    data class MainCourses(
        val name: String? = null,
        val description: String? = null,
        val price: Int? = null,
        val energy: Int? = null,
        val sugar: Int? = null,
        val fat: Int? = null,
        val protein: Int? = null,
        val picture_path: String? = null): DishesType()

    data class Desserts(
        val name: String? = null,
        val description: String? = null,
        val price: Int? = null,
        val energy: Int? = null,
        val sugar: Int? = null,
        val fat: Int? = null,
        val protein: Int? = null,
        val picture_path: String? = null): DishesType()
}
