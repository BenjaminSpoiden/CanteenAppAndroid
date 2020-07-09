package be.technifutur.devmob9.projet_cantinapp_android.utils

import be.technifutur.devmob9.projet_cantinapp_android.R

object Constants {
    const val FIREBASE_TAG = "FirebaseTest"
    const val COLLECTION_ID: String = "meals"
    const val USERS: String = "users"
    const val LAYOUT_ID = R.id.recycler_view_item_id
    const val LAYOUT = R.layout.recyclerview_menu_item

    const val POSITION_1_ORDER = 1
    const val POSITION_2_PAYMENTS = 2
    const val POSITION_3_ALLERGIES = 3
    const val POSITION_4_SETTINGS = 4

    const val EMAIL = "email"
    const val PASSWORD = "password"

    val listOfAllergies = listOf(
        R.drawable.ic_gluten,
        R.drawable.ic_celery,
        R.drawable.ic_almond,
        R.drawable.ic_crustaceans,
        R.drawable.ic_egg,
        R.drawable.ic_fish,
        R.drawable.ic_lupin,
        R.drawable.ic_milk,
        R.drawable.ic_mollusc,
        R.drawable.ic_mustard,
        R.drawable.ic_sesame,
        R.drawable.ic_soybean,
        R.drawable.ic_sulfide
    )
}