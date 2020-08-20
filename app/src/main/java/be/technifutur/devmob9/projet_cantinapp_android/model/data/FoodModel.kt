package be.technifutur.devmob9.projet_cantinapp_android.model.data

import com.google.firebase.firestore.DocumentId

data class FoodModel(
    @DocumentId val id: String? = null,
    val menu: MenusModel? = null,
    val sandwiches: List<String>? = null,
    val others: List<String>? = null
)