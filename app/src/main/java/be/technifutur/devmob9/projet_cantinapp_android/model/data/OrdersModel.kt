package be.technifutur.devmob9.projet_cantinapp_android.model.data

data class OrdersModel(
    val orderIllustration: String?,
    val orderName: String,
    val orderPrice: Int,
    val orderKCal: String,
    val orderNumberOfItems: Int)