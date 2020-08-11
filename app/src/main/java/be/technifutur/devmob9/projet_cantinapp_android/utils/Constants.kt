package be.technifutur.devmob9.projet_cantinapp_android.utils

import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.model.data.AllergiesModel

object Constants {
    const val FIREBASE_TAG = "FirebaseTest"
    const val COLLECTION_ID: String = "days"
    const val USERS: String = "users"

    const val TYPE_HEADER = 0
    const val TYPE_ITEM = 1

    const val POSITION_1_PAYMENTS = 1
    const val POSITION_2_ALLERGIES = 2
    const val POSITION_3_SETTINGS = 3

    const val EMAIL = "email"
    const val PASSWORD = "password"

    val listOfAllergies = listOf<AllergiesModel>(
        AllergiesModel(R.drawable.ic_gluten, "Céréales contenant du gluten", "le blé (comme l'épeautre et le blé de Khorasan), le seigle, l'orge, l'avoine ou les espèces hybrides."),
        AllergiesModel(R.drawable.ic_celery, "Céleri", "Le céleri est une plante herbacée bisannuelle de la famille des Apiacées, cultivée comme plante potagère pour ses feuilles et sa racine tubérisée consommées comme légumes."),
        AllergiesModel(R.drawable.ic_almond, "Fruits à coque", "À savoir les amandes, les noisettes, les noix, les noix de cajou, les noix de pécan, les noix du Brésil, les noix de Macadamia et du Queensland et les pistaches. "),
        AllergiesModel(R.drawable.ic_crustaceans, "Crustacés", "Beurk"),
        AllergiesModel(R.drawable.ic_egg, "Oeuf", "Beurk"),
        AllergiesModel(R.drawable.ic_fish, "Poissons", "Beurk"),
        AllergiesModel(R.drawable.ic_lupin, "Lupin", "Beurk"),
        AllergiesModel(R.drawable.ic_milk, "Lait", "Beurk"),
        AllergiesModel(R.drawable.ic_mollusc, "Mollusques", "Beurk"),
        AllergiesModel(R.drawable.ic_mustard, "Moutarde", "Beurk"),
        AllergiesModel(R.drawable.ic_sesame, "Graines de sésame", "Beurk"),
        AllergiesModel(R.drawable.ic_soybean, "Soja", "Beurk"),
        AllergiesModel(R.drawable.ic_sulfide, "Anhydride sulfureux et sulfites", "Beurk"),
        AllergiesModel(R.drawable.ic_peanut, "Arachide", "Beurk")
    )

    val hashMapOfAllergies = hashMapOf(
        "Gluten" to R.drawable.ic_gluten,
        "Céleri" to R.drawable.ic_celery,
        "Amande" to R.drawable.ic_almond,
        "Crustacée" to R.drawable.ic_crustaceans,
        "Oeufs" to R.drawable.ic_egg,
        "Poisson" to R.drawable.ic_fish,
        "Lait" to R.drawable.ic_milk,
        "Mollusque" to R.drawable.ic_mollusc,
        "Moutarde" to R.drawable.ic_mustard,
        "Graine de sésame" to R.drawable.ic_sesame,
        "Grain de soja" to R.drawable.ic_soybean,
        "Sulfite" to R.drawable.ic_sulfide
    )
}