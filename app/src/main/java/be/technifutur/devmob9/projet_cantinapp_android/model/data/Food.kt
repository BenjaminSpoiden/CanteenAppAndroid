package be.technifutur.devmob9.projet_cantinapp_android.model.data

/**
 * Superclass that will be used for the food related models, allowing us to aggregate all the food related items in the same list
 */
open class Food {
    private val foodItemList = ArrayList<Food>()
    fun addingFoodItem(item: Food): List<Food> {
        foodItemList.add(item)
        return foodItemList
    }
}