package be.technifutur.devmob9.projet_cantinapp_android.model.data

data class MenuItemModel(
    var menuItemName: String? = null,
    var menuDescription: String? = null,
    var menuIllustration: Int? = null,
    var isHeader: Boolean = false,
    var headerName: String? = null): Food()