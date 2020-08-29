package be.technifutur.devmob9.projet_cantinapp_android.model.data

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

/**
 * Superclass that will be used for the food related models, allowing us to aggregate all the food related items in the same list
 */

@Parcelize
open class Food(
    open val name: String? = null,
    open val description: String? = null,
    open val price: Int? = null,
    open val energy: Int? = null,
    open val sugar: Int? = null,
    open val lipids: Int? = null,
    open val proteins: Int? = null,
    open val picture_path: String? = null
): Parcelable