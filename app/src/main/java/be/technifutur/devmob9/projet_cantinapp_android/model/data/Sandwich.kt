package be.technifutur.devmob9.projet_cantinapp_android.model.data

import com.google.firebase.firestore.DocumentId
import com.google.gson.annotations.SerializedName

@Deprecated("")
data class Sandwich(
    @DocumentId val id: String? = null
//    val name: String? = null,
//    val description: String? = null,
//    val picture_path: String? = null
)