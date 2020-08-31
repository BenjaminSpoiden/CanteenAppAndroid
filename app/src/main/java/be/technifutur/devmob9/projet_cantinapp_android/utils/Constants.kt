package be.technifutur.devmob9.projet_cantinapp_android.utils

import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.model.data.AllergiesModel

object Constants {
    const val FIREBASE_TAG = "FirebaseTest"
    const val COLLECTION_ID_DAYS: String = "days"
    const val USERS: String = "users"
    const val WORKDAY: String = "isWorkday"
    const val ID_STARTERS = "starters"
    const val ID_MAIN_COURSES = "main_courses"
    const val ID_DESSERTS = "desserts"
    const val ID_DAYS_MEALS = "days_meals"
    const val ID_SANDWICHES = "sandwiches"
    const val ID_CROISSANTS = "croissants"
    const val ID_DRESSINGS = "dressings"
    const val ID_DRINKS = "drinks"
    const val ID_FRUITS = "fruit"
    const val ID_YOGHURTS = "yoghurts"
    const val PICTURES = "food"

    const val TYPE_HEADER = 0
    const val TYPE_ITEM = 1

    const val POSITION_1_PAYMENTS = 1
    const val POSITION_2_ALLERGIES = 2
    const val POSITION_3_SETTINGS = 3

    const val EMAIL = "email"
    const val PASSWORD = "password"

    val listOfAllergies = listOf<AllergiesModel>(
        AllergiesModel(R.drawable.ic_gluten, "Céréales contenant du gluten", "le blé (comme l'épeautre et le blé de Khorasan), le seigle, l'orge, l'avoine ou les espèces hybrides."),
        AllergiesModel(R.drawable.ic_celery, "Céleri", "Plante herbacée bisannuelle de la famille des Apiacées, cultivée comme plante potagère pour ses feuilles et sa racine tubérisée consommées comme légumes."),
        AllergiesModel(R.drawable.ic_almond, "Fruits à coque", "À savoir les amandes, les noisettes, les noix, les noix de cajou, les noix de pécan, les noix du Brésil, les noix de Macadamia et du Queensland et les pistaches. "),
        AllergiesModel(R.drawable.ic_crustaceans, "Crustacés", "Invertébré arthropode, le plus souvent aquatique, ayant deux paires d'antennes, des yeux composés, un tégument chitineux plus ou moins imprégné de calcaire, une respiration branchiale, des anneaux (libres ou soudés) portant chacun une paire d'appendices dans la plupart des espèces."),
        AllergiesModel(R.drawable.ic_egg, "Oeuf", "Cellule vivante, environnée de réserves alimentaires et d'enveloppes protectrices."),
        AllergiesModel(R.drawable.ic_fish, "Poissons", "Animaux vertébrés aquatiques à branchies, pourvus de nageoires et dont le corps est le plus souvent couvert d'écailles."),
        AllergiesModel(R.drawable.ic_lupin, "Lupin", "Plantes dicotylédones de la famille des Fabaceae (ou légumineuses), se caractérisent notamment par la richesse en protéines de leurs graines (jusqu'à 50 %)."),
        AllergiesModel(R.drawable.ic_milk, "Lait", "Liquide biologique comestible généralement de couleur blanchâtre produit par les glandes mammaires des mammifères femelles."),
        AllergiesModel(R.drawable.ic_mollusc, "Mollusques", "Animaux non segmentés, à symétrie bilatérale quelquefois altérée. Leur corps se compose généralement d'une tête, d'une masse viscérale, et d'un pied. La masse viscérale est recouverte en tout ou partie par un manteau, qui sécrète une coquille calcaire."),
        AllergiesModel(R.drawable.ic_mustard, "Moutarde", "Condiment préparé à partir des graines d'une plante de la famille des Brassicaceae, appelée aussi moutarde."),
        AllergiesModel(R.drawable.ic_sesame, "Graines de sésame", "Graines provenant du sésame (Sesamum indicum) qui est une plante de la famille des Pédaliacées."),
        AllergiesModel(R.drawable.ic_soybean, "Soja", "La graine de soja est une graine oléoprotéagineuse et comestible. Produite par le soja , une plante annuelle originaire d’Asie orientale. Riche en protéines et en lipides, avec un profil en acides gras intéressant, cette graine qui est par ailleurs bien pourvue en vitamines du groupe B et en isoflavones, offre des atouts nutritionnels évidents."),
        AllergiesModel(R.drawable.ic_sulfide, "Anhydride sulfureux et sulfites", "Les sulfites sont des substances naturellement présentes dans certains aliments. Ils sont également utilisés comme additifs pour préserver la couleur des aliments, prolonger leur durée de conservation et prévenir la croissance de champignons ou de bactéries."),
        AllergiesModel(R.drawable.ic_peanut, "Arachide", "Plante dont le fruit comestible s'appelle cacahuète.")
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