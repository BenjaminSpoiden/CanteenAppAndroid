package be.technifutur.devmob9.projet_cantinapp_android.model.data

import com.google.firebase.firestore.DocumentId

@Deprecated("")
data class SampleTestModel(
    @DocumentId val id: String? = null,
    val menu: Menu? = null,
    val sandwiches: List<String>? = null
)