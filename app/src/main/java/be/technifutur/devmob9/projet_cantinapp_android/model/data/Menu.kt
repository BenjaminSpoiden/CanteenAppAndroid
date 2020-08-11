package be.technifutur.devmob9.projet_cantinapp_android.model.data

import com.google.firebase.firestore.DocumentId

@Deprecated("")
data class Menu(
    val starters: List<String>? = null,
    val main_courses: List<String>? = null,
    val desserts: List<String>? = null
)