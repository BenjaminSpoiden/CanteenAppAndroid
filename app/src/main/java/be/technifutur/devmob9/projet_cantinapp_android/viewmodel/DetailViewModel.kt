package be.technifutur.devmob9.projet_cantinapp_android.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import be.technifutur.devmob9.projet_cantinapp_android.model.data.Food

class DetailViewModel: ViewModel() {
    /**
     * TODO: Fonction qui permet de calculer la hauteur d'une des views selon les formules
     * TODO: pour les differents macro-nutriments
     * TODO: Renvoyer la function sous forme de liveData et l'observer dans le Fragment ?
     */
    var detailTitle = MutableLiveData<String>()
    var detailDescription = MutableLiveData<String>()
    var sugarAmount = MutableLiveData<String>()
    var fatAmount = MutableLiveData<String>()
    var proteinsAmount = MutableLiveData<String>()
    var kCalAmount = MutableLiveData<String>()

    fun setFoodItem(foodItem: Food) {
        detailTitle.value = foodItem.name
        detailDescription.value = foodItem.description
        sugarAmount.value = "${foodItem.sugar}g"
        fatAmount.value = "${foodItem.lipids}g"
        proteinsAmount.value = "${foodItem.proteins}g"
        kCalAmount.value = "${foodItem.energy}Kcall"
    }
}