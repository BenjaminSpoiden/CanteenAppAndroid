package be.technifutur.devmob9.projet_cantinapp_android.model.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlin.Int

@Parcelize
data class OrdersModel(
    val orderIllustration: String? = null,
    val orderName: String? = null,
    val orderPrice: Int? = null,
    val orderNumberOfItems: Int? = null,
    val isHeader: Boolean = false,
    val headerName: String? = null,
    val foodList: List<Food>? = null): Food(), Parcelable