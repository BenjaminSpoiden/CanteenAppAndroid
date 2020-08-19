package be.technifutur.devmob9.projet_cantinapp_android

import android.app.Application
import be.technifutur.devmob9.projet_cantinapp_android.model.firebase.*
import be.technifutur.devmob9.projet_cantinapp_android.viewmodel.factory.*
import com.google.firebase.iid.FirebaseInstanceId
import com.jakewharton.threetenabp.AndroidThreeTen
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class CantinappApplication: Application(), KodeinAware {

    override val kodein: Kodein = Kodein.lazy {
        bind() from singleton { AuthManager() }
        bind() from singleton { ContactPageManager() }
        bind() from singleton { DishesManager() }
        bind() from singleton { CalendarDayManager() }
        bind() from provider {
            AuthViewModelFactory(
                instance()
            )
        }
        bind() from provider {
            CalendarViewModelFactory(instance())
        }

        bind() from provider {
            ContactPageVMFactory(instance())
        }

        bind() from provider {
            DishesViewModelFactory(instance())
        }
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        FirebaseInstanceId.getInstance().instanceId
    }
}